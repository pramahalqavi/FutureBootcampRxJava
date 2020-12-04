package com.example.testapp.fragment

import android.app.Dialog
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.edit
import com.example.testapp.CalendarActivity

import com.example.testapp.R


import com.example.testapp.activity.Main2Activity
import com.example.testapp.activity.ProductActivity
import com.example.testapp.activity.WebviewActivity
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_first_menu.*

class FirstMenuFragment : Fragment() {
    var counter = 0
    lateinit var txtCounter: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_first_menu, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val btnStart = view.findViewById<Button>(R.id.btnStart)
        val editTxt = view.findViewById<EditText>(R.id.txtInput)
        if (arguments != null) {
            editTxt.setText(arguments?.getString("mText"))
        }
        val btnProduct = view.findViewById<Button>(R.id.btnProduct)
        val btnDialog = view.findViewById<Button>(R.id.btnDialog)

        txtCounter = view.findViewById<TextView>(R.id.textCounter)

        val btnInc = view.findViewById<Button>(R.id.btnCounter)
        val btnIncReset = view.findViewById<Button>(R.id.btnResetCounter)
        val btnCalendar = view.findViewById<Button>(R.id.btnCalendar)

        btnStart.setOnClickListener {
            val intent = Intent(activity, Main2Activity::class.java)
            intent.putExtra("INPUT", editTxt?.text.toString())
            startActivity(intent)
        }
        btnProduct.setOnClickListener {
            val intent = Intent(activity, ProductActivity::class.java)
            startActivity(intent)
        }
        btnDialog.setOnClickListener {
            context?.let {
                val dialog = Dialog(it)
                dialog.setContentView(R.layout.dialog_custom)
                dialog.show()
            }
        }
        btnCalendar.setOnClickListener {
            val intent = Intent(activity, CalendarActivity::class.java)
            startActivity(intent)
        }

        //let, run, with, apply - kotlin
        btnInc.setOnClickListener {
            ++counter
            txtCounter.text = counter.toString()
        }
        btnIncReset.setOnClickListener {
            counter = 0
            txtCounter.text = counter.toString()
        }

        val btnView = view.findViewById<Button>(R.id.btnWeb)
        btnWeb.setOnClickListener {
            val intent = Intent(activity, WebviewActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("counter", counter)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        counter = savedInstanceState?.getInt("counter", 0) ?: 0
        txtCounter.text = counter.toString()
    }

    override fun onDestroy() {
        if (activity?.getSharedPreferences("MyPreferences", MODE_PRIVATE) != null) {
            val prefEdit: SharedPreferences =
                requireActivity().getSharedPreferences("MyPreferences", MODE_PRIVATE)
            prefEdit.edit {
                putInt("counter", counter)
            }
        }
        super.onDestroy()
    }
}
