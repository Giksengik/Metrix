package com.company.metrix.ui.servicesEmployee

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.company.metrix.databinding.CharacteristicItemBinding
import com.company.metrix.data.model.CharacteristicInfo

class CharacteristicAdapter(
    private val data: MutableList<CharacteristicInfo>,
    private val clickListener: (id: String, isSelected: Boolean) -> Unit
) :
    RecyclerView.Adapter<CharacteristicAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CharacteristicItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    fun setData(data: List<CharacteristicInfo>) {
        val size = itemCount
        this.data.clear()
        notifyItemRangeRemoved(0, size)
        this.data.addAll(data)
        notifyItemRangeInserted(0, itemCount)
    }

    class ViewHolder(
        private val binding: CharacteristicItemBinding,
        private val clickListener: (id: String, isSelected: Boolean) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(characteristic: CharacteristicInfo) {
            with(binding) {
                characteristicEmoji.text = characteristic.emoji
                characteristicName.text = characteristic.textShort
                characteristicEmoji.alpha = 0.4f
                root.setOnClickListener {
                    val isSelected = characteristicEmoji.alpha == 1.0f
                    characteristicEmoji.alpha = if (!isSelected) 1.0f else 0.4f
                    clickListener(characteristic.id, isSelected)
                }
            }
        }
    }

}