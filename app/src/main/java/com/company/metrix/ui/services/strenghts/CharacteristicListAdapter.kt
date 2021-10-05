package com.company.metrix.ui.services.strenghts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.company.metrix.databinding.CharacteristicItemLayoutBinding
import com.company.metrix.data.model.CharacteristicInfo
import com.company.metrix.data.model.Estimation

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
            when(strength.skillName){
                "Вежливость"->{
                    binding.characteristicEmoji.text = "\uD83D\uDE0A"
                }
                "Мобильность"->{
                    binding.characteristicEmoji.text = "\uD83D\uDCF1"
                }
                "Профессионализм"->{
                    binding.characteristicEmoji.text = "\uD83D\uDC4D"
                }
                "Скорость"->{
                    binding.characteristicEmoji.text = "⌚"
                }
                "Дружелюбность"->{
                    binding.characteristicEmoji.text = "\uD83E\uDD17"
                }
            }
//            binding.characteristicEmoji.text = strenth.emoji
//            binding.strengthText.text = strenth.text
        }
    }
}