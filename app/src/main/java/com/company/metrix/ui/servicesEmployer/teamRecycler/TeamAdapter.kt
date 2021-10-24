package com.company.metrix.ui.servicesEmployer.teamRecycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.company.metrix.databinding.TeamItemBinding

class TeamAdapter(private val onClickListener : OnTeamClickListener) : ListAdapter<TeamModel, TeamAdapter.ViewHolder>(SkillDiffUtil()) {

    interface OnTeamClickListener {
        fun onTeamClick(teamModel: TeamModel, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            TeamItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClickListener
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class SkillDiffUtil : DiffUtil.ItemCallback<TeamModel>() {
        override fun areItemsTheSame(oldItem: TeamModel, newItem: TeamModel): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: TeamModel, newItem: TeamModel): Boolean =
            oldItem == newItem
    }

    class ViewHolder(val binding: TeamItemBinding, private val onClickListener : OnTeamClickListener) : RecyclerView.ViewHolder(binding.root)  {

        fun bind(team: TeamModel) {
            binding.teamName.text = team.teamName + team.peopleCount

            itemView.setOnClickListener {
                onClickListener.onTeamClick(team, position)
            }

        }
    }
}