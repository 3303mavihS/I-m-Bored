package com.imbored

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class RecentViewPagerAdapter( activity: FragmentActivity ):FragmentStateAdapter(activity)  {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->RecentRecyclerViewActivity()
            else->ProfileSetting()
        }
    }
}