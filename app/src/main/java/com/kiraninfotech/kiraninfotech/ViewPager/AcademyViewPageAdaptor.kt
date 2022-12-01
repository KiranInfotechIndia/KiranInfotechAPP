package com.kiraninfotech.kiraninfotech.ViewPager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.kiraninfotech.kiraninfotech.ClassAcademyFragment
import com.kiraninfotech.kiraninfotech.CourseAcademyFragment
import com.kiraninfotech.kiraninfotech.LocateAcademyFragment

class AcademyViewPageAdaptor (fm : FragmentManager) : FragmentStatePagerAdapter(fm) {
    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        return if (position == 0){
         LocateAcademyFragment()
        }else if (position == 1){
            CourseAcademyFragment()
        }else{
            ClassAcademyFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if (position == 0){
            "Locate Us"
        }else if (position == 1){
            "Course"
        }else{
            "Classes"
        }
    }
}