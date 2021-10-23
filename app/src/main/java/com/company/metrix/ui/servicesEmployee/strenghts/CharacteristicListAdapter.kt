package com.company.metrix.ui.servicesEmployee.strenghts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.company.metrix.data.model.Estimation
import com.company.metrix.databinding.CharacteristicItemLayoutBinding

class CharacteristicListAdapter :
    ListAdapter<Estimation, CharacteristicListAdapter.ViewHolder>(StrengthDiffUtil()) {


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

    class StrengthDiffUtil : DiffUtil.ItemCallback<Estimation>() {
        override fun areItemsTheSame(oldItem: Estimation, newItem: Estimation): Boolean =
            oldItem.comment == newItem.comment


        override fun areContentsTheSame(oldItem: Estimation, newItem: Estimation): Boolean =
            oldItem == newItem

    }

    class ViewHolder(val binding: CharacteristicItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(strength: Estimation) {
            binding.strengthText.text = strength.comment
            when (strength.skillName) {
                "Вежливость" -> {
                    binding.characteristicEmoji.text = "\uD83D\uDE0A"
                    binding.strengthText.text = "Вежливость. " + strength.comment
                }
                "Мобильность" -> {
                    binding.characteristicEmoji.text = "\uD83D\uDCF1"
                    binding.strengthText.text = "Мобильность. " + strength.comment
                }
                "Профессионализм" -> {
                    binding.characteristicEmoji.text = "\uD83D\uDC4D"
                    binding.strengthText.text = "Профессионализм. " + strength.comment
                }
                "Скорость" -> {
                    binding.characteristicEmoji.text = "⌚"
                    binding.strengthText.text = "Скорость. " + strength.comment
                }
                "Дружелюбность" -> {
                    binding.characteristicEmoji.text = "\uD83E\uDD17"
                    binding.strengthText.text = "Дружелюбность. " + strength.comment
                }
                "Рекомендация" -> {
                    binding.characteristicEmoji.text = "\uD83D\uDCAC"
                    binding.strengthText.text = strength.comment
                }
            }
//            binding.characteristicEmoji.text = strenth.emoji
//            binding.strengthText.text = strenth.text
        }
    }
}