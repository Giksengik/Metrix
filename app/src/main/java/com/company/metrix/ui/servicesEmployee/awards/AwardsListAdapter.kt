package com.company.metrix.ui.servicesEmployee.awards

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.company.metrix.databinding.AwardsItemBinding
import com.company.metrix.data.model.AwardInfo

class AwardsListAdapter :
    ListAdapter<AwardInfo, AwardsListAdapter.ViewHolder>(AwardInfoDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            AwardsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class AwardInfoDiffUtil : DiffUtil.ItemCallback<AwardInfo>() {
        override fun areItemsTheSame(oldItem: AwardInfo, newItem: AwardInfo): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: AwardInfo, newItem: AwardInfo): Boolean =
            oldItem == newItem

    }

    class ViewHolder(val binding: AwardsItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(awardInfo: AwardInfo) {
            binding.awardName.text = awardInfo.name
            val awardNominationsAdapter = AwardNominationListAdapter()
            binding.awardNominationsList.apply{
                adapter = awardNominationsAdapter
                layoutManager = LinearLayoutManager(binding.root.context,
                LinearLayoutManager.HORIZONTAL, false)
            }
            awardNominationsAdapter.submitList(awardInfo.nominations)
        }
    }
}