package com.example.testapp.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.os.HandlerCompat.postDelayed
import androidx.core.os.postDelayed
import com.example.testapp.R
import kotlinx.android.synthetic.main.activity_main2.*
import org.w3c.dom.Text
import java.util.*

class Main2Activity : AppCompatActivity() {
    lateinit var txtCounter: TextView
    var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val txtExtra = findViewById<TextView>(R.id.textExtra)
        val ext: String? = intent.getStringExtra("INPUT")
        txtExtra.text = ext

        val toolbar = findViewById<Toolbar>(R.id.my_toolbar2)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        txtCounter = findViewById<TextView>(R.id.textCounter)
        if (savedInstanceState != null) {
            counter = savedInstanceState.getInt("counter")
            txtCounter.text = counter.toString()
        }
        val btnInc = findViewById<Button>(R.id.btnCounter)
        val btnIncReset = findViewById<Button>(R.id.btnResetCounter)
        btnInc.setOnClickListener {
            ++counter
            txtCounter.text = counter.toString()
        }
        btnIncReset.setOnClickListener {
            counter = 0
            txtCounter.text = counter.toString()
        }

        val emailSub = findViewById<EditText>(R.id.emailSub)
        val emailContent = findViewById<EditText>(R.id.emailContent)
        val btnEmail = findViewById<Button>(R.id.btnEmail)
        btnEmail.setOnClickListener {
            if (emailSub.text.toString().isEmpty() || emailContent.text.toString().isEmpty()) {
                Toast.makeText(this,"Subject and Content cannot be empty",Toast.LENGTH_SHORT).show()
            } else {
                intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:13515132@std.stei.itb.ac.id"))
                intent.putExtra(Intent.EXTRA_SUBJECT,emailSub.text.toString())
                intent.putExtra(Intent.EXTRA_TEXT,emailContent.text.toString())
                startActivity(Intent.createChooser(intent,"Choose"))
            }
        }

        val randomVal = findViewById<TextView>(R.id.randomVal)
        randomVal.text = (1..99).random().toString()
//        val runnable = object: Runnable {
//            override fun run() {
//                randomVal.text = (1..99).random().toString()
//                Handler().postDelayed(this,5000)
//            }
//        }
//        Handler().postDelayed(runnable,5000)

        val worker = object: Thread() {
            override fun run() {
                while(!this.isInterrupted) {
                    sleep(5000)
                    runOnUiThread {
                        randomVal.text = (1..99).random().toString()
                    }
                }
            }
        }
        worker.start()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("counter",counter)
    }
}
