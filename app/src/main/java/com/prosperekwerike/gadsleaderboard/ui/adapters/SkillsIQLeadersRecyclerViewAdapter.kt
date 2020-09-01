package com.prosperekwerike.gadsleaderboard.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.prosperekwerike.gadsleaderboard.R
import com.prosperekwerike.gadsleaderboard.databinding.SkillsIqLeaderLayoutItemBinding
import com.prosperekwerike.gadsleaderboard.domain.SkillsIQLeadersCustomModel

class SkillsIQLeadersRecyclerViewAdapter :
    ListAdapter<SkillsIQLeadersCustomModel, SkillsIQLeadersRecyclerViewAdapter.LayoutItemViewHolder>
        (skillsIQLeadersRecyclerViewDiffUtil){

    class LayoutItemViewHolder(private var binding : SkillsIqLeaderLayoutItemBinding)
        : RecyclerView.ViewHolder(binding.root){

        fun bindData(data : SkillsIQLeadersCustomModel){
            binding.skillsIQLeaderCustomModel = data
        }

        companion object{
            fun createViewHolder(parent : ViewGroup) : LayoutItemViewHolder{
                val binding =
                    DataBindingUtil.inflate<SkillsIqLeaderLayoutItemBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.skills_iq_leader_layout_item,
                    parent,
                    false
                )

                return LayoutItemViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LayoutItemViewHolder {
        return LayoutItemViewHolder.createViewHolder(parent)
    }

    override fun onBindViewHolder(holder: LayoutItemViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}

private val skillsIQLeadersRecyclerViewDiffUtil =
    object : DiffUtil.ItemCallback<SkillsIQLeadersCustomModel>(){
        override fun areItemsTheSame(
            oldItem: SkillsIQLeadersCustomModel,
            newItem: SkillsIQLeadersCustomModel
        ): Boolean {
            return oldItem.leaderName == newItem.leaderName
        }

        override fun areContentsTheSame(
            oldItem: SkillsIQLeadersCustomModel,
            newItem: SkillsIQLeadersCustomModel
        ): Boolean {
            return oldItem == newItem
        }

    }