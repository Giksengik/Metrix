package com.company.metrix.ui.services.strenghts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.company.metrix.databinding.CharacteristicItemLayoutBinding
import com.company.metrix.data.model.CharacteristicInfo

class CharacteristicListAdapter :
    ListAdapter<CharacteristicInfo, CharacteristicListAdapter.ViewHolder>(StrengthDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            CharacteristicItemLayoutBinding.inflate(
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
            oldItem.emoji == newItem.emoji


        override fun areContentsTheSame(oldItem: CharacteristicInfo, newItem: CharacteristicInfo): Boolean =
            oldItem == newItem

    }

    class ViewHolder(val binding: CharacteristicItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(strenth: CharacteristicInfo) {
            binding.characteristicEmoji.text = strenth.emoji
            binding.strengthText.text = strenth.text
        }
    }
}