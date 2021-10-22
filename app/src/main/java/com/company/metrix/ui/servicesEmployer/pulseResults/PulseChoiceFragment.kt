package com.company.metrix.ui.servicesEmployer.pulseResults

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.company.metrix.databinding.FragmentPulseResultBinding
import com.company.metrix.databinding.FragmentPulseResultChoiceBinding
import com.company.metrix.ui.servicesEmployer.TeamViewModel
import com.company.metrix.ui.servicesEmployer.teamRecycler.TeamAdapter
import com.company.metrix.ui.servicesEmployer.teamRecycler.TeamModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class PulseChoiceFragment : Fragment() {
    private lateinit var adapter: TeamAdapter
    private val viewModel: TeamViewModel by viewModels()

    private var _binding: FragmentPulseResultChoiceBinding? = null
    private val binding get() = _binding!!

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
        _binding = FragmentPulseResultChoiceBinding.inflate(layoutInflater)
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
                    PulseChoiceFragmentDirections.actionPulseResultChoiceFragmentToPulseResultFragment(teamModel)
                findNavController().navigate(action)
            }
        }
        adapter = TeamAdapter(clickListener)
    }
}

