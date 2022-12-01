package com.kiraninfotech.kiraninfotech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.view.MenuItem
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class AcademyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_academy)

        setSupportActionBar(toolbar)

        val toolbarSubTitle = "Certified Tally Academy | Tally Education Pvt Ltd"
        val spannableToolBarSubTitle = SpannableString(toolbarSubTitle)

        spannableToolBarSubTitle.setSpan(RelativeSizeSpan(0.6f), 0,toolbarSubTitle.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        supportActionBar!!.let {
            it.title = "Kiran Academy"
            it.subtitle = spannableToolBarSubTitle
            it.setDisplayHomeAsUpEnabled(true)
        }

        loadFragment(AcademyFragment(), false)

        bottom_nav.menu.findItem(R.id.btnHome).apply {
            setIcon(R.drawable.ic_course)
            setTitle(R.string.training)
        }

        bottom_nav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.btnHome ->{
                    loadFragment(AcademyFragment(), true)
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


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            android.R.id.home ->{
                super.onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}