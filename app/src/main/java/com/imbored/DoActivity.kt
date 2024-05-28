package com.imbored

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.imbored.apicalls.ConnectionRetrofitBuilder
import com.imbored.database.ActivityEntry
import com.imbored.database.ProjectActivityDatabase
import com.imbored.databinding.ActivityDoBinding


class DoActivity : AppCompatActivity(), PopupMenu.OnMenuItemClickListener {

    //declaration
    private lateinit var bridgeModel : BridgeModel
    private lateinit var bridgeModelFactory : BridgeModelFactory
    private lateinit var repo : ResponseRepo
    private lateinit var projectActivityDatabase : ProjectActivityDatabase
    private lateinit var binding : ActivityDoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoBinding.inflate(layoutInflater)
        val view = binding.root
        enableEdgeToEdge()
        setContentView(view)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //fetching the name from the sharedPreferences
        val readSharedPreferences = getSharedPreferences("activity-name", MODE_PRIVATE)
        val readName = readSharedPreferences.getString("name","")
        val avatar = readSharedPreferences.getInt("avatar",0)

        binding.greet.text = "Hi, $readName"
        binding.avatar.setImageResource(avatar)

        init()
        var responseActivity =""
        bridgeModel.activityLiveDataFromAPI.observe(this){
            Log.i("ResponseFromAPIBored",it.toString())
            binding.yourActivity.text = it.activity
            responseActivity = it.activity
            if (responseActivity=="")


            if (it.link=="")
                binding.activityLink.text = ""
            else
                binding.activityLink.text = "Link : " + it.link
        }


        var start = true
        binding.nextBtn.setOnClickListener{
            if(checkInternetConnectivity()) {
                bridgeModel.getActivityFromAPI()
                binding.saveBtn.visibility = View.VISIBLE
                start = false
            }
            else {
                binding.yourActivity.text = "Check Internet Connectivity."
                binding.nextBtn.text = "Check"
                start = false
            }

            if (responseActivity=="") {
                binding.yourActivity.text = "There seems some problem with the server."
                binding.saveBtn.visibility = View.INVISIBLE
            }

            if(!start){
                binding.nextBtn.text = "Next"
            }
        }

        binding.saveBtn.setOnClickListener {
            bridgeModel.insert(
                ActivityEntry(
                    activity = binding.yourActivity.text.toString()
                )
            )
            binding.saveBtn.visibility = View.INVISIBLE
            Toast.makeText(this,"Congratulation on completing the task.",Toast.LENGTH_LONG).show()
        }

        binding.clickForRecent.setOnClickListener{
            val menuPopUp = PopupMenu(this,it)
            val inflater : MenuInflater = menuPopUp.menuInflater
            inflater.inflate(R.menu.menu,menuPopUp.menu)
            menuPopUp.setOnMenuItemClickListener(this@DoActivity)
            menuPopUp.show()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onResume() {
        super.onResume()
        //fetching the name from the sharedPreferences
        val readSharedPreferences = getSharedPreferences("activity-name", MODE_PRIVATE)
        val readName = readSharedPreferences.getString("name","")
        val avatar = readSharedPreferences.getInt("avatar",0)

        binding.greet.text = "Hi, $readName"
        binding.avatar.setImageResource(avatar)
    }

    private fun init() {
        projectActivityDatabase = ProjectActivityDatabase(this)
        repo = ResponseRepo(ConnectionRetrofitBuilder.getInstance(),projectActivityDatabase.getDatabaseDao())
        bridgeModelFactory = BridgeModelFactory(repo)
        bridgeModel = ViewModelProvider(this,bridgeModelFactory)[BridgeModel::class.java]
    }

    private fun checkInternetConnectivity(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        // Handle menu item clicks here
        when (item.itemId) {
            R.id.show_recent -> {
                // Start the desired activity when menu item 1 is clicked
                startActivity(Intent(this, Recent::class.java))
                return true
            }
            // Add more cases for other menu items if needed
            else -> return false
        }
    }

}


