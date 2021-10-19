package com.company.metrix.ui.servicesEmployee.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.company.metrix.BackButtonHandler
import com.company.metrix.databinding.FragmentTeamBinding
import com.company.metrix.ui.servicesEmployee.strenghts.FragmentStrengthsDirections
import com.company.metrix.ui.support.setupNavigation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentTeam : Fragment(), BackButtonHandler {
    private val viewModel: TeamViewModel by viewModels()
    private lateinit var binding: FragmentTeamBinding
    private lateinit var teamMembersListAdapter: TeamMembersListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.apply {
            viewModelScope.launch {
                getMembersOfTeam(1)
            }
        }

        requireActivity().setupNavigation(this, FragmentTeamDirections.actionFragmentTeamToServiceFragment())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTeamBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.team.observe(viewLifecycleOwner, {
            teamMembersListAdapter.submitList(viewModel.team.value)
        })

        setupOnBackButtonPressed()
        setupTeamMembersList()
    }


    private fun setupTeamMembersList() {
        teamMembersListAdapter = TeamMembersListAdapter()
        binding.teamList.apply {
            adapter = teamMembersListAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    override fun setupOnBackButtonPressed() {
        binding.backButton.setOnClickListener {
            val action = FragmentTeamDirections.actionFragmentTeamToServiceFragment()
            findNavController().navigate(action)
        }
    }
}