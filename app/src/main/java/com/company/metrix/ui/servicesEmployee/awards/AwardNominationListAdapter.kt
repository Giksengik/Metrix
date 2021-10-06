package com.company.metrix.ui.servicesEmployee.awards

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.company.metrix.databinding.AwardItemBinding
import com.company.metrix.data.model.AwardNomination

class AwardNominationListAdapter :
    ListAdapter<AwardNomination, AwardNominationListAdapter.ViewHolder>(AwardNominationDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            AwardItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class AwardNominationDiffUtil : DiffUtil.ItemCallback<AwardNomination>() {
        override fun areItemsTheSame(oldItem: AwardNomination, newItem: AwardNomination): Boolean =
            oldItem.type == newItem.type

        override fun areContentsTheSame(
            oldItem: AwardNomination,
            newItem: AwardNomination
        ): Boolean =
            oldItem == newItem
    }

    class ViewHolder(val binding: AwardItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(awardNomination: AwardNomination) {
           binding.awardIcon.setImageDrawable(
               ResourcesCompat.getDrawable(
                   binding.root.resources,
                   awardNomination.type.iconId,
                   binding.root.context.theme
               )
           )
            if(!awardNomination.isReceived){
                val color: Int = Color.parseColor("#AA757272")
                binding.awardIcon.setColorFilter(color)
            }
        }
    }
}