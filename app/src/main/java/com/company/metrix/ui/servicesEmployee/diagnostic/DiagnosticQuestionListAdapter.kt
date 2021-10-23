package com.company.metrix.ui.servicesEmployee.diagnostic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.company.metrix.data.model.Diagnostic
import com.company.metrix.databinding.DiagnosticQuestionsItemBinding

class DiagnosticQuestionListAdapter(private val onClickListener: OnDiagnosticQuestionClickListener) :
    ListAdapter<Diagnostic, DiagnosticQuestionListAdapter.ViewHolder>(QuestionDiffUtils()) {

    interface OnDiagnosticQuestionClickListener {
        fun onDiagnosticClick(model: Diagnostic, position: Int, isYes: Boolean)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DiagnosticQuestionsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClickListener
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class QuestionDiffUtils : DiffUtil.ItemCallback<Diagnostic>() {
        override fun areItemsTheSame(oldItem: Diagnostic, newItem: Diagnostic): Boolean =
            oldItem.value == newItem.value

        override fun areContentsTheSame(oldItem: Diagnostic, newItem: Diagnostic): Boolean =
            oldItem == newItem

    }

    class ViewHolder(
        val binding: DiagnosticQuestionsItemBinding,
        private val onClickListener: OnDiagnosticQuestionClickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(question: Diagnostic) {
            with(binding) {
                questionText.text = question.value

                yesCheckBox.setOnClickListener {
                    onClickListener.onDiagnosticClick(question, adapterPosition, true)
                }

                noCheckBox.setOnClickListener {
                    onClickListener.onDiagnosticClick(question, adapterPosition, false)
                }

//            itemView.setOnClickListener {
//                onClickListener.onDiagnosticClick(question, adapterPosition)
//            }
            }
        }
    }

}
