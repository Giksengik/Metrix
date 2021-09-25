package com.company.metrix.services.diagnostic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.company.metrix.databinding.DiagnosticQuestionsItemBinding
import com.company.metrix.model.Question

class DiagnosticQuestionListAdapter :
    ListAdapter<Question, DiagnosticQuestionListAdapter.ViewHolder>(QuestionDiffUtils()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DiagnosticQuestionsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class QuestionDiffUtils : DiffUtil.ItemCallback<Question>() {
        override fun areItemsTheSame(oldItem: Question, newItem: Question): Boolean =
            oldItem.value == newItem.value

        override fun areContentsTheSame(oldItem: Question, newItem: Question): Boolean =
            oldItem == newItem

    }

    class ViewHolder(val binding: DiagnosticQuestionsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(question: Question) {
            binding.questionText.text = question.value
        }
    }

}
