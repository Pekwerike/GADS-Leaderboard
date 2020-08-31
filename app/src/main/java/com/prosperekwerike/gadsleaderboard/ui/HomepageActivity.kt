package com.prosperekwerike.gadsleaderboard.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import com.prosperekwerike.gadsleaderboard.R
import com.prosperekwerike.gadsleaderboard.databinding.ActivityHomepageBinding
import com.prosperekwerike.gadsleaderboard.viewmodels.HomepageActivityViewModel

class HomepageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomepageBinding
    private lateinit var homepageToolbar : Toolbar
    private lateinit var homepageViewPager : ViewPager
    private val homepageViewModel: HomepageActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_homepage)
        initializeViews()

    }

    private fun initializeViews() {
        homepageToolbar = binding.mainToolbar
        homepageViewPager = binding.mainViewpager
    }
}