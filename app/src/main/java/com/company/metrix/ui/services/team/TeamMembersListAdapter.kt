package com.company.metrix.ui.services.team

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.company.metrix.data.model.User
import com.company.metrix.databinding.TeamMemberItemBinding

class TeamMembersListAdapter : ListAdapter<User, TeamMembersListAdapter.ViewHolder>(
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

    class TeamMemberDiffUtil : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
            oldItem == newItem
    }

    class ViewHolder(val binding: TeamMemberItemBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(teamMemberInfo: User) {
            binding.memberInfo.text = "${teamMemberInfo.name }, ${teamMemberInfo.role}"
        }
    }

}