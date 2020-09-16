package com.prosperekwerike.gadsleaderboardren.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.prosperekwerike.gadsleaderboardren.ui.fragments.LearningLeadersFragment
import com.prosperekwerike.gadsleaderboardren.ui.fragments.SkillIQLeaders

class HomepageViewPagerAdapter(
    private val numberOfPages : Int, fm: FragmentManager
) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
    return when(position){
           0 -> LearningLeadersFragment()
           1 -> SkillIQLeaders()
           else -> LearningLeadersFragment()
       }
    }

    override fun getCount(): Int = numberOfPages

}