package com.company.metrix.services.strenghts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.company.metrix.databinding.StrengthItemLayoutBinding
import com.company.metrix.model.CharacteristicInfo

class CharacteristicListAdapter :
    ListAdapter<CharacteristicInfo, CharacteristicListAdapter.ViewHolder>(StrengthDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            StrengthItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class StrengthDiffUtil : DiffUtil.ItemCallback<CharacteristicInfo>() {
        override fun areItemsTheSame(oldItem: CharacteristicInfo, newItem: CharacteristicInfo): Boolean =
            oldItem.iconId == newItem.iconId


        override fun areContentsTheSame(oldItem: CharacteristicInfo, newItem: CharacteristicInfo): Boolean =
            oldItem == newItem

    }

    class ViewHolder(val binding: StrengthItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(strenth: CharacteristicInfo) {
            binding.strengthIcon.setImageDrawable(
                ResourcesCompat.getDrawable(
                    binding.root.resources,
                    strenth.iconId,
                    binding.root.context.theme
                )
            )
            binding.strengthText.text = binding.root.context.getString(strenth.textId)
        }
    }
}