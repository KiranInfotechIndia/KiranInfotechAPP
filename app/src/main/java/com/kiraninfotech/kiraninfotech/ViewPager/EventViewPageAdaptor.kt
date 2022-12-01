package com.kiraninfotech.kiraninfotech.ViewPager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.kiraninfotech.kiraninfotech.OfflineEventFragment
import com.kiraninfotech.kiraninfotech.OnlineEventFragment

class EventViewPageAdaptor(fm : FragmentManager) : FragmentStatePagerAdapter(fm) {
    override fun getCount(): Int {
        return 2;
    }

    override fun getItem(position: Int): Fragment {
        return if (position == 0){
            OnlineEventFragment()
        }else{
            OfflineEventFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if (position == 0){
            "Online"
        }else{
            "Offline"
        }
    }
}