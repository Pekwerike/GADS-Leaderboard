package com.prosperekwerike.gadsleaderboard.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.prosperekwerike.gadsleaderboard.R
import com.prosperekwerike.gadsleaderboard.databinding.ActivityHomepageBinding
import com.prosperekwerike.gadsleaderboard.models.LearningLeadersCustomModel
import com.prosperekwerike.gadsleaderboard.ui.adapters.HomepageViewPagerAdapter
import com.prosperekwerike.gadsleaderboard.viewmodels.HomepageActivityViewModel

class HomepageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomepageBinding
    private lateinit var homepageTabLayout : TabLayout
    private lateinit var homepageViewPager : ViewPager
    private val homepageViewModel: HomepageActivityViewModel by viewModels()

    companion object{
        val learningLeadersList : MutableLiveData<MutableList<LearningLeadersCustomModel>> by lazy {
            MutableLiveData<MutableList<LearningLeadersCustomModel>>()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_homepage)
        initializeViews()
        homepageViewPager.adapter = HomepageViewPagerAdapter(2, supportFragmentManager)
        observeLiveDataFromViewModel()

    }

    private fun observeLiveDataFromViewModel(){
        homepageViewModel.learningLeadersCollection.observe(this, Observer{
            it?.let {
                learningLeadersList.value = it
            }
        })
    }

    private fun initializeViews() {
        homepageTabLayout = binding.mainToolbar
        homepageViewPager = binding.mainViewpager
    }
}