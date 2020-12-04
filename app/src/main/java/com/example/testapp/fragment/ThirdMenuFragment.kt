package com.example.testapp.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.testapp.R
import com.example.testapp.adapter.CustomAdapter
import com.example.testapp.adapter.CustomRecyclerAdapter
import com.example.testapp.model.Capsule

class ThirdMenuFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_third_menu, container, false)

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
        val data4 = Capsule()
        data4.name = "Jellybean"
        data4.releaseDate = "June 27, 2012"
        data4.version = "Android 4.1"

        val listItem = ArrayList<Capsule>()
        listItem.add(data1)
        listItem.add(data2)
        listItem.add(data3)
        listItem.add(data4)

        val mListRecyclerView = view.findViewById<RecyclerView>(R.id.list_recycler_view)
        mListRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        mListRecyclerView.addItemDecoration(DividerItemDecoration(mListRecyclerView.context, DividerItemDecoration.VERTICAL))
        val mAdapter = CustomRecyclerAdapter(listItem, requireContext(), onClickListener = { view: View, capsule: Capsule ->
            Toast.makeText(requireContext(), "Clicked " + capsule.name, Toast.LENGTH_SHORT).show()
        })
        mListRecyclerView.adapter = mAdapter

        return view
    }
}