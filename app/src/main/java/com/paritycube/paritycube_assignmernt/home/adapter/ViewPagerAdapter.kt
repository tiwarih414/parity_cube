package com.paritycube.paritycube_assignmernt.home.adapter

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.paritycube.paritycube_assignmernt.home.fragment.FeaturedFragment
import com.paritycube.paritycube_assignmernt.home.fragment.PopularFragment
import com.paritycube.paritycube_assignmernt.home.fragment.TopFragment

class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        fragment = when (position) {
            0 -> {
                TopFragment()
            }
            1 -> {
                PopularFragment()
            }
            else -> {
                FeaturedFragment()
            }
        }
        return fragment
    }

    override fun getCount() = 3

    override fun getPageTitle(position: Int): CharSequence? {
        var title = ""
        when (position) {
            0 -> {
                title = "TOP"
            }
            1 -> {
                title = "POPULAR"
            }
            2 -> {
                title = "FEATURED"
            }
        }
        return title
    }
}