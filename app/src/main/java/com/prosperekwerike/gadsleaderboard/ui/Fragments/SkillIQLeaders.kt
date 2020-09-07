package com.prosperekwerike.gadsleaderboard.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.prosperekwerike.gadsleaderboard.R
import com.prosperekwerike.gadsleaderboard.databinding.FragmentSkillIQLeadersBinding
import com.prosperekwerike.gadsleaderboard.ui.activities.HomepageActivity
import com.prosperekwerike.gadsleaderboard.ui.adapters.SkillsIQLeadersRecyclerViewAdapter


class SkillIQLeaders : Fragment() {
    private lateinit var binding: FragmentSkillIQLeadersBinding
    private lateinit var skillsIQLeadersRecyclerView: RecyclerView
    private lateinit var swipeToRefreshCollectionOfSkillsIQLeaders: SwipeRefreshLayout
    private lateinit var skillsIQLeadersRecyclerViewAdapter: SkillsIQLeadersRecyclerViewAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_skill_i_q_leaders,
            container,
            false
        )
        initializeViews()
        skillsIQLeadersRecyclerViewAdapter = SkillsIQLeadersRecyclerViewAdapter()
        skillsIQLeadersRecyclerView.adapter = skillsIQLeadersRecyclerViewAdapter
        observeLiveDataFromHomepageActivity()
        skillsIQLeadersRecyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )

        swipeToRefreshCollectionOfSkillsIQLeaders.setOnRefreshListener {
            if (swipeToRefreshCollectionOfSkillsIQLeaders.isRefreshing) {
                HomepageActivity.refreshSkillsIQLeaders.value = true
            }
        }


        return binding.root
    }

    private fun observeLiveDataFromHomepageActivity() {
        HomepageActivity.skillsIQLeadersList.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it.size == 0) {
                    swipeToRefreshCollectionOfSkillsIQLeaders.isRefreshing = true
                    HomepageActivity.refreshSkillsIQLeaders.value = true
                } else if(it.size != 0 && swipeToRefreshCollectionOfSkillsIQLeaders.isRefreshing){
                    swipeToRefreshCollectionOfSkillsIQLeaders.isRefreshing = false
                    skillsIQLeadersRecyclerViewAdapter.submitList(it)
                    displayMessage("updated")
                }else {
                    skillsIQLeadersRecyclerViewAdapter.submitList(it)
                }
            }
        })

        HomepageActivity.networkErrorWhileLoadingData.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it && swipeToRefreshCollectionOfSkillsIQLeaders.isRefreshing) {
                    swipeToRefreshCollectionOfSkillsIQLeaders.isRefreshing = false
                    displayMessage("Couldn't refresh list")
                    HomepageActivity.networkErrorWhileLoadingData.value = false
                }
            }
        })
    }


    private fun displayMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun initializeViews() {
        skillsIQLeadersRecyclerView = binding.skillsIqLeadersRecyclerview
        swipeToRefreshCollectionOfSkillsIQLeaders =
            binding.swipeToRefreshCollectionOfSkillsIqLeaders
    }


}