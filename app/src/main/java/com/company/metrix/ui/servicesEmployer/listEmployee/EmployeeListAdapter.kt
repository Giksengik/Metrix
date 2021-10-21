package com.company.metrix.ui.servicesEmployer.listEmployee

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.company.metrix.data.model.Estimation
import com.company.metrix.data.model.Team
import com.company.metrix.data.model.User
import com.company.metrix.databinding.EmployeeItemBinding
import com.company.metrix.databinding.SwipeTeamMemberBinding
import com.company.metrix.swipe.ActionBindHelper
import com.company.metrix.swipe.SwipeAction
import com.company.metrix.swipe.SwipeMenuListener
import com.company.metrix.ui.servicesEmployer.teamRecycler.TeamModel

class EmployeeListAdapter(
    private val onClickListener: OnEmployeeClickListener,
    private val teamNames: List<Team>
) : ListAdapter<User, EmployeeListAdapter.ViewHolder>(
    SkillDiffUtil()
) {

    interface OnEmployeeClickListener {
        fun onEmployeeClick(user: User, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            EmployeeItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClickListener,
            teamNames
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class SkillDiffUtil : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
            oldItem == newItem
    }

    class ViewHolder(
        val binding: EmployeeItemBinding,
        private val onClickListener: OnEmployeeClickListener,
        private val teamNames: List<Team>
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
//            for (team in teamNames) {
//                if (team.team_id == user.teamId)
//                    binding.employeeInfo.text = user.name + ", " + team.team_name
//            }
            binding.employeeInfo.text = user.name + ", "
            binding.employeeRole.text = user.role

            itemView.setOnClickListener {
                onClickListener.onEmployeeClick(user, position)
            }

        }
    }

}