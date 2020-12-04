package com.example.testapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.model.Capsule

class CustomRecyclerAdapter(val items: ArrayList<Capsule>, val context: Context, val onClickListener: (View, Capsule) -> Unit) :
    RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_item_capsule,null,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomRecyclerAdapter.ViewHolder, position: Int) {
        val capsule = items[position]
        holder.name.text = capsule.name
        holder.version.text = capsule.version
        holder.date.text = capsule.releaseDate
        holder.itemView.setOnClickListener { view ->
            onClickListener.invoke(view,capsule)
//            Toast.makeText(context, "Clicked " + capsule.name, Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val name = itemView.findViewById<TextView>(R.id.li_name)
        val version = itemView.findViewById<TextView>(R.id.li_version)
        val date = itemView.findViewById<TextView>(R.id.li_date)
    }
}