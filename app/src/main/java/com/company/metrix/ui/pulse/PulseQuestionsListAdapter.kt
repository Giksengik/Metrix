package com.company.metrix.ui.pulse

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.company.metrix.databinding.PulseItemBinding
import com.company.metrix.data.model.PulseQuestion
import com.company.metrix.data.model.User


class PulseQuestionsListAdapter(val onClick: OnPulseQuestionsListener) :
    ListAdapter<PulseQuestion, PulseQuestionsListAdapter.ViewHolder>
        (PulseQuestionDiffUtil()) {

    interface OnPulseQuestionsListener {
        fun onPulseClick(value: String, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            PulseItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClick
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class PulseQuestionDiffUtil : DiffUtil.ItemCallback<PulseQuestion>() {
        override fun areItemsTheSame(oldItem: PulseQuestion, newItem: PulseQuestion): Boolean =
            oldItem.question == newItem.question

        override fun areContentsTheSame(oldItem: PulseQuestion, newItem: PulseQuestion): Boolean =
            oldItem == newItem

    }

    class ViewHolder(
        val binding: PulseItemBinding,
        val onClickQuestion: OnPulseQuestionsListener
    ) : RecyclerView.ViewHolder(binding.root) {


        fun bind(pulseQuestion: PulseQuestion) {
            binding.questionValue.text = pulseQuestion.question

            val pos = position

             val onClick = object : PulseAnswersListAdapter.OnPulseAnswersListener {
                override fun onPulseClick(value: String, position: Int) {
                    onClickQuestion.onPulseClick(value, pos)
                }
            }
            val answersListAdapter = PulseAnswersListAdapter(onClick)
            binding.answersList.apply {
                adapter = answersListAdapter
                layoutManager = LinearLayoutManager(binding.root.context)
            }
            answersListAdapter.submitList(pulseQuestion.answers)
        }
    }
}