package com.company.metrix.ui.services.team

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.company.metrix.databinding.TeamMemberItemBinding
import com.company.metrix.data.model.TeamMemberInfo

class TeamMembersListAdapter : ListAdapter<TeamMemberInfo, TeamMembersListAdapter.ViewHolder>(
    TeamMemberDiffUtil()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            TeamMemberItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(getItem(position))
    }

    class TeamMemberDiffUtil : DiffUtil.ItemCallback<TeamMemberInfo>() {
        override fun areItemsTheSame(oldItem: TeamMemberInfo, newItem: TeamMemberInfo): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: TeamMemberInfo, newItem: TeamMemberInfo): Boolean =
            oldItem == newItem

    }

    class ViewHolder(val binding: TeamMemberItemBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(teamMemberInfo: TeamMemberInfo) {
            binding.memberInfo.text = "${teamMemberInfo.name }, ${teamMemberInfo.position}"
        }
    }

}