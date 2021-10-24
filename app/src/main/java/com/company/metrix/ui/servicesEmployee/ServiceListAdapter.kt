package com.company.metrix.ui.servicesEmployee

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.company.metrix.databinding.ServiceItemBinding

class ServiceListAdapter(private val clickListener: OnServiceClickListener) :
    ListAdapter<ServiceType, ServiceListAdapter.ViewHolder>
        (ServiceTypeDiffUtil()) {

    interface OnServiceClickListener {
        fun onServiceClick(serviceType: ServiceType)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val holder = ViewHolder(
            ServiceItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        holder.binding.root.setOnClickListener {
            if (holder.adapterPosition != RecyclerView.NO_POSITION) {
                clickListener.onServiceClick(getItem(holder.adapterPosition))
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ServiceTypeDiffUtil : DiffUtil.ItemCallback<ServiceType>() {
        override fun areItemsTheSame(oldItem: ServiceType, newItem: ServiceType): Boolean =
            oldItem.iconId == newItem.iconId

        override fun areContentsTheSame(oldItem: ServiceType, newItem: ServiceType): Boolean =
            newItem == oldItem

    }

    class ViewHolder(val binding: ServiceItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(serviceType: ServiceType) {
            binding.serviceIcon.setImageDrawable(
                ResourcesCompat.getDrawable(
                    binding.root.resources,
                    serviceType.iconId,
                    binding.root.context.theme
                    )
            )
            binding.serviceName.text = binding.root.context.getText(serviceType.textId)
        }
    }

}