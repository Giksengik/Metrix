package com.company.metrix.ui.servicesEmployer.diagnosticResult

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.company.metrix.databinding.FragmentDiagnosticTeamChoiceBinding
import com.company.metrix.ui.servicesEmployer.TeamViewModel
import com.company.metrix.ui.servicesEmployer.teamRecycler.TeamAdapter
import com.company.metrix.ui.servicesEmployer.teamRecycler.TeamModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DiagnosticTeamChoiceFragment : Fragment() {
    private var _binding: FragmentDiagnosticTeamChoiceBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: TeamAdapter
    private val viewModel: TeamViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.viewModelScope.launch {
            viewModel.getTeamsOfUser()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDiagnosticTeamChoiceBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

        viewModel.teamList.observe(viewLifecycleOwner, {
            adapter.submitList(viewModel.teamList.value)
            binding.teamsList.adapter = adapter
        })
    }

    private fun init() {
        binding.strengthsContent.visibility = View.VISIBLE
        binding.loadingBar.visibility = View.GONE

        val clickListener = object : TeamAdapter.OnTeamClickListener {
            override fun onTeamClick(teamModel: TeamModel, position: Int) {
                val action =
                    DiagnosticTeamChoiceFragmentDirections.actionDiagnosticTeamChoiceFragmentToDiagnosticResultFragment(teamModel)
                findNavController().navigate(action)
            }
        }
        adapter = TeamAdapter(clickListener)
    }
}

