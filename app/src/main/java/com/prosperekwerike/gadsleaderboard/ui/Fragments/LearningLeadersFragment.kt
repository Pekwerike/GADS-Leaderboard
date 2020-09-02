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
import com.prosperekwerike.gadsleaderboard.databinding.FragmentLearningLeadersBinding
import com.prosperekwerike.gadsleaderboard.ui.HomepageActivity
import com.prosperekwerike.gadsleaderboard.ui.adapters.LearningLeadersRecyclerViewAdapter

class LearningLeadersFragment : Fragment() {
    private lateinit var binding: FragmentLearningLeadersBinding
    private lateinit var learningLeadersRecyclerView: RecyclerView
    private lateinit var swipeToRefreshLearningLeaders: SwipeRefreshLayout
    private lateinit var learningLeadersRecyclerViewAdapter: LearningLeadersRecyclerViewAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_learning_leaders,
            container,
            false
        )
        initializeViews()
        learningLeadersRecyclerViewAdapter = LearningLeadersRecyclerViewAdapter()
        learningLeadersRecyclerView.adapter = learningLeadersRecyclerViewAdapter
        observeLiveDataFromHomepageActivity()

        learningLeadersRecyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL, false
        )

        swipeToRefreshLearningLeaders.setOnRefreshListener {
            if (swipeToRefreshLearningLeaders.isRefreshing) {
                HomepageActivity.refreshLearningLeaders.value = true
            }
        }

        return binding.root
    }

    private fun observeLiveDataFromHomepageActivity() {
        HomepageActivity.learningLeadersList.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it.size == 0) {
                    HomepageActivity.refreshLearningLeaders.value = true
                    swipeToRefreshLearningLeaders.isRefreshing = true
                } else if(it.size != 0 && swipeToRefreshLearningLeaders.isRefreshing) {
                    swipeToRefreshLearningLeaders.isRefreshing = false
                    learningLeadersRecyclerViewAdapter.submitList(it)
                    displayMessage("updated")
                }else {
                    learningLeadersRecyclerViewAdapter.submitList(it)
                }
            }
        })

        HomepageActivity.networkErrorWhileLoadingData.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it && swipeToRefreshLearningLeaders.isRefreshing) {
                    swipeToRefreshLearningLeaders.isRefreshing = false
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
        learningLeadersRecyclerView = binding.learningLeadersRecyclerview
        swipeToRefreshLearningLeaders = binding.swipeToRefreshCollectionOfLearningLeaders
    }

}