package com.imbored

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.imbored.apicalls.ConnectionRetrofitBuilder
import com.imbored.database.ActivityEntry
import com.imbored.database.ProjectActivityDatabase

class RecentRecyclerViewActivity : Fragment(), RecentRecyclerViewAdapter.OnItemClickListener {

    // Declaration
    private lateinit var bridgeModel: BridgeModel
    private lateinit var bridgeModelFactory: BridgeModelFactory
    private lateinit var repo: ResponseRepo
    private lateinit var projectActivityDatabase: ProjectActivityDatabase
    private lateinit var recyclerViewAdapter: RecentRecyclerViewAdapter
    private lateinit var context: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_recyclerview_recent, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // Initialize RecyclerView and Adapter
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewRecent)
        val textView: TextView = view.findViewById(R.id.noActivity)
        recyclerView.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, true)

        recyclerViewAdapter = RecentRecyclerViewAdapter(emptyList(),this) // Initialize with empty list

        // Initialize ViewModel and observe data changes
        init()
        bridgeModel.getActivityDataFromDatabase().observe(viewLifecycleOwner) {
            Log.i("Data From Database",it.toString())
            if(it.toString() == "[]"){
                textView.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            }
            else{
                textView.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
            }
            // Update adapter with new data
            recyclerViewAdapter = RecentRecyclerViewAdapter(it,this)
            recyclerView.adapter = recyclerViewAdapter
        }

    }

    private fun init() {
        projectActivityDatabase = ProjectActivityDatabase(context)
        repo = ResponseRepo(
            ConnectionRetrofitBuilder.getInstance(),
            projectActivityDatabase.getDatabaseDao()
        )
        bridgeModelFactory = BridgeModelFactory(repo)
        bridgeModel = ViewModelProvider(this, bridgeModelFactory)[BridgeModel::class.java]
    }

    override fun delete(activityEntry: ActivityEntry) {
        bridgeModel.delete(activityEntry)
    }
}
