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

class AwsPlanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aws_plan)

        // ToolBar
        setSupportActionBar(toolbar)

        val toolbarSubTitle = getString(R.string.toolbarSubTitle)
        val spannableToolBarSubTitle = SpannableString(toolbarSubTitle)

        spannableToolBarSubTitle.setSpan(RelativeSizeSpan(0.6f), 0,toolbarSubTitle.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        supportActionBar!!.let {
            it.subtitle = spannableToolBarSubTitle
            it.setDisplayHomeAsUpEnabled(true)
        }

        loadFragment(AwsPlansFragment(), false)

        bottom_nav.menu.findItem(R.id.btnHome).apply {
            setIcon(R.drawable.ic_aws)
            setTitle(R.string.awsPlans)
        }

        bottom_nav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.btnHome ->{
                    loadFragment(AwsPlansFragment(), true)
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