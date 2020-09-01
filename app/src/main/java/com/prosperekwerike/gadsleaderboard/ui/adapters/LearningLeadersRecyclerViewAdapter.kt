package com.prosperekwerike.gadsleaderboard.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.prosperekwerike.gadsleaderboard.R
import com.prosperekwerike.gadsleaderboard.databinding.LearningLeaderLayoutItemBinding
import com.prosperekwerike.gadsleaderboard.models.LearningLeadersCustomModel

class LearningLeadersRecyclerViewAdapter : ListAdapter<LearningLeadersCustomModel,
        LearningLeadersRecyclerViewAdapter.LayoutViewHolder>
    (learningLeadersRecyclerViewAdapterDiffUtil) {

    class LayoutViewHolder(
        private var binding: LearningLeaderLayoutItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(data: LearningLeadersCustomModel) {
            binding.learningLeaderModel = data
        }

        companion object {
            fun createViewHolder(parent: ViewGroup): LayoutViewHolder {
                val layoutBinding =
                    DataBindingUtil.inflate<LearningLeaderLayoutItemBinding>(
                        LayoutInflater.from(parent.context),
                        R.layout.learning_leader_layout_item,
                        parent,
                        false
                    )

                return LayoutViewHolder(layoutBinding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LayoutViewHolder {
        return LayoutViewHolder.createViewHolder(parent)
    }

    override fun onBindViewHolder(holder: LayoutViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}

private val learningLeadersRecyclerViewAdapterDiffUtil =
    object :
        DiffUtil.ItemCallback<LearningLeadersCustomModel>() {
        override fun areItemsTheSame(
            oldItem: LearningLeadersCustomModel,
            newItem: LearningLeadersCustomModel
        ): Boolean {
            return oldItem.leaderName == newItem.leaderName
        }

        override fun areContentsTheSame(
            oldItem: LearningLeadersCustomModel,
            newItem: LearningLeadersCustomModel
        ): Boolean {
            return oldItem == newItem
        }

    }