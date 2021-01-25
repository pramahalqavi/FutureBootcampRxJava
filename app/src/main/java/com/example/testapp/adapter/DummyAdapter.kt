package com.example.testapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testapp.R
import com.example.testapp.model.Capsule
import com.example.testapp.model.Pokemon
import org.w3c.dom.Text

class DummyAdapter(private var ctx: Context, private var items: ArrayList<Pokemon>) :
    RecyclerView.Adapter<DummyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_item_poke, null)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = items[position]
        holder.name?.text = pokemon.name
//        holder.image?.let { Glide.with(ctx).load(pokemon.url).into(it) }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var name: TextView? = itemView.findViewById(R.id.li_name)
        var image: ImageView? = itemView.findViewById(R.id.li_img)
    }

}