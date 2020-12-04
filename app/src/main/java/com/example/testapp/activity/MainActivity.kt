package com.example.testapp.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.testapp.R
import com.example.testapp.fragment.FirstMenuFragment
import com.example.testapp.fragment.SecondMenuFragment
import com.example.testapp.fragment.ThirdMenuFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private var currMenu = 1
    private lateinit var mContent: Fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        currMenu = 1

        if (savedInstanceState != null) {
            mContent = supportFragmentManager.getFragment(savedInstanceState, "myFragment") ?: FirstMenuFragment()
            openFragment(mContent)
        } else {
            mContent = FirstMenuFragment()
            val bundle = Bundle()
            bundle.putString("mText","INPUT")
            mContent.arguments = bundle
            openFragment(mContent)
        }

        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.navigation_1 -> {
                    if (currMenu != 1) {
                        val firstMenuFrag = FirstMenuFragment()
                        openFragment(firstMenuFrag)
                        currMenu = 1
                    }
                }
                R.id.navigation_2 -> {
                    if (currMenu != 2) {
                        val secondMenuFrag = SecondMenuFragment()
                        openFragment(secondMenuFrag)
                        currMenu = 2
                    }
                }
                R.id.navigation_3 -> {
                    if (currMenu != 3) {
                        val thirdMenuFrag = ThirdMenuFragment()
                        openFragment(thirdMenuFrag)
                        currMenu = 3
                    }
                }
            }
            return@setOnNavigationItemSelectedListener true
        }

        Log.d("TAGG","create")
    }

    private fun openFragment(fragment: Fragment?) {
        val transaction = supportFragmentManager.beginTransaction()
        fragment?.let {
            transaction.replace(R.id.container, it).commit()
            mContent = it
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        supportFragmentManager.putFragment(outState,"myFragment",mContent)
    }

    override fun onResume() {
        super.onResume()
        Log.d("TAGG","resume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("TAGG","pause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("TAGG","stop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TAGG","destroy")
    }
}
