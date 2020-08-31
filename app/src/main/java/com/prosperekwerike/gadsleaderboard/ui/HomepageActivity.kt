package com.prosperekwerike.gadsleaderboard.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.prosperekwerike.gadsleaderboard.R
import com.prosperekwerike.gadsleaderboard.databinding.ActivityHomepageBinding
import com.prosperekwerike.gadsleaderboard.ui.adapters.HomepageViewPagerAdapter
import com.prosperekwerike.gadsleaderboard.viewmodels.HomepageActivityViewModel

class HomepageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomepageBinding
    private lateinit var homepageTabLayout : TabLayout
    private lateinit var homepageViewPager : ViewPager
    private val homepageViewModel: HomepageActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_homepage)
        initializeViews()
        homepageViewPager.adapter = HomepageViewPagerAdapter(2, supportFragmentManager)
        homepageTabLayout.addTab()
    }

    private fun initializeViews() {
        homepageTabLayout = binding.mainToolbar
        homepageViewPager = binding.mainViewpager
    }
}