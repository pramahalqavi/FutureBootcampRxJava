package com.example.testapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toolbar
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import com.example.testapp.R
import com.example.testapp.fragment.ProductDetailFragment

class ProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.my_toolbar)
        toolbar.setNavigationOnClickListener {

        }
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        openFragment(ProductDetailFragment())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_product_actionbar,menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun openFragment(fragment: Fragment?) {
        val transaction = supportFragmentManager.beginTransaction()
        if (fragment != null) {
            transaction.replace(R.id.container, fragment)
            transaction.commit()
        }
    }
}
