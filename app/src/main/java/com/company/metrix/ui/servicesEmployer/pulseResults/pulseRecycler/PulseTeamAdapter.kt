package com.company.metrix.ui.servicesEmployer.pulseResults.pulseRecycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.company.metrix.databinding.TeamItemBinding

class PulseTeamAdapter : ListAdapter<PulseTeamModel, PulseTeamAdapter.ViewHolder>(SkillDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            TeamItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class SkillDiffUtil : DiffUtil.ItemCallback<PulseTeamModel>() {
        override fun areItemsTheSame(oldItem: PulseTeamModel, newItem: PulseTeamModel): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: PulseTeamModel, newItem: PulseTeamModel): Boolean =
            oldItem == newItem
    }

    class ViewHolder(val binding: TeamItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(name: PulseTeamModel) {
            binding.teamName.text = "${name.teamName} ,"
            binding.peopleCount.text = " число человек : ${name.peopleCount}"
        }
    }
}