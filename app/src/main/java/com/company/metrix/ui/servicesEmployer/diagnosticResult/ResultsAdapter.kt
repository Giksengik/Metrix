package com.company.metrix.ui.servicesEmployer.diagnosticResult

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.company.metrix.databinding.DiagnosticResultItemBinding

class ResultsAdapter(
) : ListAdapter<String, ResultsAdapter.ViewHolder>( //TODO model
    SkillDiffUtil()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            DiagnosticResultItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class SkillDiffUtil : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
            oldItem == newItem
    }

    class ViewHolder(
        val binding: DiagnosticResultItemBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        //TODO
        fun bind(value: String) {
            binding.content.text = value

        }
    }

}