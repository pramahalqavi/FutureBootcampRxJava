package com.example.testapp.adapter

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.model.Capsule

class CustomAdapter(private var ctx: Context, private var items: ArrayList<Capsule>) :
    BaseAdapter() {
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    private class CapsuleItemViewHolder {
        internal var name: TextView? = null
        internal var version: TextView? = null
        internal var date: TextView? = null
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        var holder = CapsuleItemViewHolder()
        if (view == null) {
            val inflater = LayoutInflater.from(ctx)
            view = inflater.inflate(R.layout.row_item_capsule,null,false)

            holder = CapsuleItemViewHolder()
            holder.name = view.findViewById(R.id.li_name)
            holder.version = view.findViewById(R.id.li_version)
            holder.date = view.findViewById(R.id.li_date)
        }
        val capsule = items[position]
        holder.name?.text = capsule.name
        holder.version?.text = capsule.version
        holder.date?.text = capsule.releaseDate

        return view as View
    }

}