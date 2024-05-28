package com.imbored

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.imbored.database.ActivityEntry

class RecentRecyclerViewAdapter(
    private val list: List<ActivityEntry>,
    private val onitemclickListener: OnItemClickListener
):RecyclerView.Adapter<RecentRecyclerViewAdapter.ListItemViewHolder>(){

    class ListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val activityTextView : TextView = itemView.findViewById(R.id.activityView)
        val rmvBtn : TextView = itemView.findViewById(R.id.rmv)
    }

    interface OnItemClickListener{
        fun delete(activityEntry: ActivityEntry)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recent_activity_listitem_layout,parent,false)
        return ListItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        val listItem = list[position]
        holder.activityTextView.text = listItem.activity
        holder.rmvBtn.setOnClickListener{
            onitemclickListener.delete(listItem)
        }
    }

}