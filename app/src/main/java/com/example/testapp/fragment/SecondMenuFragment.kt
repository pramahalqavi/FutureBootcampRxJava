package com.example.testapp.fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

import com.example.testapp.R
import com.example.testapp.activity.Main2Activity
import com.example.testapp.adapter.CustomAdapter
import com.example.testapp.model.Capsule

class SecondMenuFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d("TAGG","secondMenu")
        val view: View = inflater.inflate(R.layout.fragment_second_menu, container, false)

        val data1 = Capsule()
        data1.name = "Marshmallow"
        data1.releaseDate = "October 5, 2015"
        data1.version = "Android 6.0"
        val data2 = Capsule()
        data2.name = "Lollipop"
        data2.releaseDate = "November 12, 2014"
        data2.version = "Android 5.0"
        val data3 = Capsule()
        data3.name = "Kitkat"
        data3.releaseDate = "October 31, 2013"
        data3.version = "Android 4.4"

        val listItem = ArrayList<Capsule>()
        listItem.add(data1)
        listItem.add(data2)
        listItem.add(data3)

        val adapter = CustomAdapter(requireContext(),listItem)
        val lView = view.findViewById<ListView>(R.id.listview)
        lView.adapter = adapter

        lView.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(requireContext(), "Clicked " + listItem.get(position).name ,Toast.LENGTH_SHORT).show()
        }

        return view
    }
}
