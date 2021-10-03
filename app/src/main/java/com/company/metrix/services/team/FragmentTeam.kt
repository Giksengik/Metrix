package com.company.metrix.services.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.company.metrix.BackButtonHandler
import com.company.metrix.databinding.FragmentTeamBinding
import com.company.metrix.model.TeamMemberInfo

class FragmentTeam : Fragment(), BackButtonHandler {

    private lateinit var binding : FragmentTeamBinding
    private var teamMembersListAdapter : TeamMembersListAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTeamBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupOnBackButtonPressed()
        setupTeamMembersList()
    }

    private fun setupTeamMembersList() {
        teamMembersListAdapter = TeamMembersListAdapter()
        binding.teamList.apply {
            adapter = teamMembersListAdapter
            layoutManager = LinearLayoutManager(activity)
        }
        setupDummyMembers()
    }

    private fun setupDummyMembers() {
        teamMembersListAdapter?.submitList(
            listOf(
                TeamMemberInfo(
                    "Степанов Илья",
                    "руководитель аналитического отдела"
                ),
                TeamMemberInfo(
                    "Большиков Степан",
                    "главный аналитик"
                ),
                TeamMemberInfo(
                    "Колесников Алексей",
                    "аналитик"
                ),
                TeamMemberInfo(
                    "Безбах Анатолий",
                    "аналитик"
                )
            )
        )
    }

    override fun setupOnBackButtonPressed() {
        binding.backButton.setOnClickListener{
            activity?.onBackPressed()
        }
    }
}