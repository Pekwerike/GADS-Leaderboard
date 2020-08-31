package com.prosperekwerike.gadsleaderboard.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.prosperekwerike.gadsleaderboard.R
import com.prosperekwerike.gadsleaderboard.databinding.FragmentLearningLeadersBinding
import com.prosperekwerike.gadsleaderboard.ui.adapters.LearningLeadersRecyclerViewAdapter

class LearningLeadersFragment : Fragment() {
    private lateinit var binding : FragmentLearningLeadersBinding
    private lateinit var learningLeadersRecyclerView : RecyclerView
    private lateinit var learningLeadersRecyclerViewAdapter : LearningLeadersRecyclerViewAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
        R.layout.fragment_learning_leaders,
        container,
        false)
        initializeViews()
        learningLeadersRecyclerViewAdapter = LearningLeadersRecyclerViewAdapter()
        learningLeadersRecyclerView.adapter = learningLeadersRecyclerViewAdapter
        //TODO submit the list of learning leaders fetched from the api

        learningLeadersRecyclerView.layoutManager = LinearLayoutManager(requireContext(),
        LinearLayoutManager.VERTICAL, false)

        return binding.root
    }

    private fun initializeViews(){
        learningLeadersRecyclerView = binding.learningLeadersRecyclerview
    }

}