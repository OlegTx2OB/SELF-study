package com.example.todoapprecycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private var mList: MutableList<ToDoItem>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>()
{

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val title: TextView = itemView.findViewById(R.id.item_recycler_title)
        val description: TextView = itemView.findViewById(R.id.item_recycler_description)
        val number: TextView =itemView.findViewById(R.id.item_recycler_number)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = mList[position].title
        holder.description.text = mList[position].description
        holder.number.text = mList[position].number.toString()
    }

    fun updateList(updatedList: List<ToDoItem>)
    {
        mList = updatedList.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}