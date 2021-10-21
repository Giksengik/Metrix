package com.company.metrix.ui.pulse

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.company.metrix.R
import com.company.metrix.data.model.User
import com.company.metrix.databinding.AnswerItemBinding
import java.lang.IllegalArgumentException

class PulseAnswersListAdapter(val onClickListener: OnPulseAnswersListener) :
    ListAdapter<String, PulseAnswersListAdapter.ViewHolder>(PulseAnswerDiffUtil()) {

    interface OnPulseAnswersListener {
        fun onPulseClick(value: String, position: Int)
    }

    private var selected: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(
            AnswerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClickListener
        )
        holder.binding.root.setOnClickListener {
            if (holder.adapterPosition != RecyclerView.NO_POSITION) {
                onItemChecked(holder.adapterPosition)
            }
        }
        return holder
    }

    private fun onItemChecked(position: Int) {
        val oldPosition = selected
        if (position == oldPosition && position >= 0) {
            selected = -1
            notifyItemChanged(oldPosition)
        } else {
            selected = position
            if (oldPosition >= 0)
                notifyItemChanged(oldPosition)
            notifyItemChanged(position)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), position, selected)
    }

    class PulseAnswerDiffUtil : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
            oldItem == newItem

    }

    class ViewHolder(val binding: AnswerItemBinding, val onClickListener: OnPulseAnswersListener) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(question: String, position: Int, selected: Int?) {
            binding.answerValue.text = question
            if (position != selected) {
                binding.answerPositionIcon.setImageDrawable(
                    when (position) {
                        0 -> ResourcesCompat.getDrawable(
                            binding.root.resources,
                            R.drawable.ic_first_choice,
                            binding.root.context.theme
                        )
                        1 -> ResourcesCompat.getDrawable(
                            binding.root.resources,
                            R.drawable.ic_second_choice,
                            binding.root.context.theme
                        )
                        2 -> ResourcesCompat.getDrawable(
                            binding.root.resources,
                            R.drawable.ic_third_choice,
                            binding.root.context.theme
                        )
                        3 -> ResourcesCompat.getDrawable(
                            binding.root.resources,
                            R.drawable.ic_fourth_choice,
                            binding.root.context.theme
                        )
                        else -> throw IllegalArgumentException("Illegal num of questions")
                    }
                )
            } else {
                onClickListener.onPulseClick(question, position)

                binding.answerPositionIcon.setImageDrawable(
                    when (position) {
                        0 -> {
                            ResourcesCompat.getDrawable(
                                binding.root.resources,
                                R.drawable.ic_first_selected_choice,
                                binding.root.context.theme
                            )
                        }
                        1 -> ResourcesCompat.getDrawable(
                            binding.root.resources,
                            R.drawable.ic_second_selected_choice,
                            binding.root.context.theme
                        )
                        2 -> ResourcesCompat.getDrawable(
                            binding.root.resources,
                            R.drawable.ic_third_selected_choice,
                            binding.root.context.theme
                        )
                        3 -> ResourcesCompat.getDrawable(
                            binding.root.resources,
                            R.drawable.ic_fourth_selected_choice,
                            binding.root.context.theme
                        )
                        else -> throw IllegalArgumentException("Illegal num of questions")
                    }
                )
            }

        }
    }
}