package com.company.metrix.ui.servicesEmployer.teams.recyclerEmployee

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.company.metrix.data.model.Estimation
import com.company.metrix.data.model.User
import com.company.metrix.databinding.SwipeTeamMemberBinding
import com.company.metrix.databinding.UserItemBinding
import com.company.metrix.swipe.ActionBindHelper
import com.company.metrix.swipe.SwipeAction
import com.company.metrix.swipe.SwipeMenuListener
import kotlin.math.roundToInt

typealias OnActionClick = (user: User, action: SwipeAction) -> Unit

class EmployeeAdapter(
    private val onClickListener: OnEmployeeClickListener,
    private val estimList: List<List<Estimation>>,
    private val onActionClicked: OnActionClick
) : ListAdapter<User, EmployeeAdapter.ViewHolder>(
    SkillDiffUtil()
) {

    interface OnEmployeeClickListener {
        fun onEmployeeClick(teamModel: User, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            SwipeTeamMemberBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClickListener,
            onActionClicked,
            estimList
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
        val binding: SwipeTeamMemberBinding,
        val onClickListener: OnEmployeeClickListener,
        private val onActionClick: OnActionClick,
        private val estimList: List<List<Estimation>>
    ) :
        RecyclerView.ViewHolder(binding.root), SwipeMenuListener {
        private lateinit var item: User
        private val actionsBindHelper = ActionBindHelper()

        fun bind(user: User) {
            item = user
            binding.swipeToAction.menuListener = this
            binding.data.nameInTeam.text = "${user.name}, ${user.role}"
            var average = 0.0
            for (estim in estimList.indices) {
                if (estimList[estim].isNotEmpty() ) {
                    average = estimList[estim].map { it.rate }.average()
                }
            }
            binding.data.ratingText.text = average.toString().substring(0, 3)
            binding.data.ratingBar.rating = average.toFloat()


            itemView.setOnClickListener {
                onClickListener.onEmployeeClick(user, position)
            }

        }

        override fun onClosed(view: View) {
        }

        override fun onOpened(view: View) {
            val data = item
            actionsBindHelper.closeOtherThan(data.name)
        }

        override fun onFullyOpened(view: View, quickAction: SwipeAction) {
        }

        override fun onActionClicked(view: View, action: SwipeAction) {
            onActionClick(item, action)
            binding.swipeToAction.close()
        }
    }
}
