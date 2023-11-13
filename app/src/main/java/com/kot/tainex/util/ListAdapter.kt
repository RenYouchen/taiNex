package com.kot.tainex.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.kot.tainex.R
import com.kot.tainex.data.activitys
import com.kot.tainex.databinding.CompoentActivitysBinding

class ListAdapter(private val data : List<activitys>) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var item : RelativeLayout
        var time : TextView
        var place1 : TextView
        var place2 : TextView
        var title : TextView
        init {
            item = view.findViewById(R.id.list_item_root)
            time = view.findViewById(R.id.textViewTime)
            place1 = view.findViewById(R.id.place1)
            place2 = view.findViewById(R.id.place2)
            title = view.findViewById(R.id.title)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.compoent_activitys,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //TODO ADD A CLICKABLE AND MOVE TO A NEW ACTIVITY
        holder.item.setOnClickListener{
            println(position)
        }
        holder.time.text = data[position].dateTime
        holder.title.text = data[position].title
        holder.place1.text = ""
        holder.place2.text = ""
        data[position].hall.forEach{
            if (it == "1館") holder.place1.text = "1館"
            if (it == "2館") holder.place2.text = "2館"
        }
    }


}