package com.imbored

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.imbored.databinding.ActivityGreetingPageBinding

class GreetingPage : AppCompatActivity() {

    //binding variable declaration
    private lateinit var binding : ActivityGreetingPageBinding
    //checking if sharedPreference exist or not
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGreetingPageBinding.inflate(layoutInflater)
        val view = binding.root
        enableEdgeToEdge()
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        sharedPreferences = getSharedPreferences("activity-name",MODE_PRIVATE)
        val readName = sharedPreferences.getString("name","")

        if (readName != "") {
            val intent = Intent(this, DoActivity::class.java)
            startActivity(intent)
        }

        //putting name to sharePreferences to store the name
        binding.nameSaveBtn.setOnClickListener {
            val name = binding.nameEdit.getText().toString()

            if(name!=""){
                sharedPreferences.edit {
                    putString("name",name).apply()
                }
                val intent = Intent(this, DoActivity::class.java)
                startActivity(intent)
            }
        }
    }
}