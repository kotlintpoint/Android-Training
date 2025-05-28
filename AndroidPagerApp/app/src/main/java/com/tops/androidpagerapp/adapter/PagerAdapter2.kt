package com.tops.androidpagerapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tops.androidpagerapp.model.PagerFragment
import java.util.ArrayList

class PagerAdapter2(activity: FragmentActivity, private val fragmentList: ArrayList<PagerFragment>) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position].fragment
    }
}