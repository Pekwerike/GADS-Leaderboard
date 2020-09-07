package com.prosperekwerike.gadsleaderboard.ui.activities

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
import com.prosperekwerike.gadsleaderboard.domain.LearningLeadersCustomModel
import com.prosperekwerike.gadsleaderboard.domain.SkillsIQLeadersCustomModel
import com.prosperekwerike.gadsleaderboard.ui.adapters.HomepageViewPagerAdapter
import com.prosperekwerike.gadsleaderboard.viewmodels.HomepageActivityViewModel

class HomepageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomepageBinding
    private lateinit var homepageTabLayout: TabLayout
    private lateinit var homepageViewPager: ViewPager
    private val homepageViewModel: HomepageActivityViewModel by viewModels()

    companion object {
        val learningLeadersList: MutableLiveData<MutableList<LearningLeadersCustomModel>> by lazy {
            MutableLiveData<MutableList<LearningLeadersCustomModel>>()
        }

        val skillsIQLeadersList: MutableLiveData<MutableList<SkillsIQLeadersCustomModel>> by lazy {
            MutableLiveData<MutableList<SkillsIQLeadersCustomModel>>()
        }

        val refreshSkillsIQLeaders: MutableLiveData<Boolean> by lazy {
            MutableLiveData<Boolean>(false)
        }

        val refreshLearningLeaders: MutableLiveData<Boolean> by lazy {
            MutableLiveData<Boolean>(false)
        }

        val networkErrorWhileLoadingData: MutableLiveData<Boolean> by lazy {
            MutableLiveData<Boolean>(false)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_homepage)
        initializeViews()
        homepageViewPager.adapter = HomepageViewPagerAdapter(2, supportFragmentManager)
        observeLiveDataFromViewModel()
        observeLiveDataTriggeredByFragments()

        homepageTabLayout.addTab(homepageTabLayout.newTab().setText("Learning Leaders"))
        homepageTabLayout.addTab(homepageTabLayout.newTab().setText("Skill IQ Leaders"))
        attachTabLayoutWithViewpager()

    }

    private fun observeLiveDataTriggeredByFragments() {
        refreshSkillsIQLeaders.observe(this, Observer {
            it?.let {
                if (it) {
                    homepageViewModel.refreshSkillsIQLeaders()
                    refreshSkillsIQLeaders.value = false
                }
            }
        })
        refreshLearningLeaders.observe(this, Observer {
            it?.let {
                if (it) {
                    homepageViewModel.refreshLearningLeaders()
                    refreshLearningLeaders.value = false
                }
            }
        })
    }

    private fun attachTabLayoutWithViewpager() {
        homepageViewPager.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(
                homepageTabLayout
            )
        )

        homepageTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                homepageViewPager.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                homepageViewPager.currentItem = tab!!.position
            }

        })
    }

    private fun observeLiveDataFromViewModel() {
        homepageViewModel.learningLeaders.observe(this, Observer {
            it?.let {
                learningLeadersList.value = it.toMutableList()
            }
        })

        homepageViewModel.skillIQLeaders.observe(this, Observer {
            it?.let {
                skillsIQLeadersList.value = it.toMutableList()
            }
        })

        homepageViewModel.networkError.observe(this, Observer {
            it?.let {
                if (it)
                    networkErrorWhileLoadingData.value = it

            }
        })
    }

    private fun initializeViews() {
        homepageTabLayout = binding.mainToolbar
        homepageViewPager = binding.mainViewpager
    }
}