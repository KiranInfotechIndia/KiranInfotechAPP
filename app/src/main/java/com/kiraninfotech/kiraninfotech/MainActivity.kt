package com.kiraninfotech.kiraninfotech

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import java.text.SimpleDateFormat
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // ToolBar
        setSupportActionBar(toolbar)

        val toolbarSubTitle = getString(R.string.toolbarSubTitle)
        val spannableToolBarSubTitle = SpannableString(toolbarSubTitle)

        spannableToolBarSubTitle.setSpan(RelativeSizeSpan(0.6f), 0,toolbarSubTitle.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        toolbar.subtitle = spannableToolBarSubTitle


        // Bottom NavBar

        loadFragment(HomeFragment(), false)

        bottom_nav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.btnHome ->{
                    loadFragment(HomeFragment(), true)
                }
                R.id.btnEvent ->{
                    loadFragment(EventFragment(), true)
                }
                R.id.btnSupport ->{
                    loadFragment(SupportFragment(), true)
                }
            }
            true
        }

    }
    private fun loadFragment(fragment : Fragment, IsOpen : Boolean){
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        if(IsOpen){
            ft.replace(R.id.container, fragment)
        }else{
            ft.add(R.id.container, fragment)
        }
        ft.commit()
    }

}