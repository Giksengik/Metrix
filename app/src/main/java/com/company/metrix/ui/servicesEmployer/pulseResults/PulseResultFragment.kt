package com.company.metrix.ui.servicesEmployer.pulseResults

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.navArgs
import com.company.metrix.R
import com.company.metrix.databinding.FragmentPulseResultBinding
import com.company.metrix.ui.servicesEmployer.teamRecycler.TeamModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PulseResultFragment : Fragment() {
    private val viewModel: PulseResultViewModel by viewModels()
    private val args: PulseResultFragmentArgs by navArgs()

    private var _binding: FragmentPulseResultBinding? = null
    private val binding get() = _binding!!

    private var team: TeamModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        team = args.team
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPulseResultBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loadingBar.visibility = View.GONE
        binding.content.visibility = View.VISIBLE

        binding.pulseOne.icon = R.drawable.ic_first_choice
        binding.pulseTwo.icon = R.drawable.ic_second_choice
        binding.pulseThree.icon = R.drawable.ic_third_choice
        binding.pulseFour.icon = R.drawable.ic_fourth_choice

        binding.q2PulseFirst.icon = R.drawable.ic_first_choice
        binding.q2PulseSecond.icon = R.drawable.ic_second_choice
        binding.q2PulseThird.icon = R.drawable.ic_third_choice
        binding.q2PulseFourth.icon = R.drawable.ic_fourth_choice

        viewModel.viewModelScope.launch {
            team?.let { viewModel.getTeamPulseResults(it.teamId) }

            binding.pulseOne.percent = viewModel.percent1Q1.value!!
            binding.pulseTwo.percent =  viewModel.percent2Q1.value!!
            binding.pulseThree.percent =  viewModel.percent3Q1.value!!
            binding.pulseFour.percent =  viewModel.percent4Q1.value!!

            binding.q2PulseFirst.percent = viewModel.percent1Q2.value!!
            binding.q2PulseSecond.percent = viewModel.percent2Q2.value!!
            binding.q2PulseThird.percent = viewModel.percent3Q2.value!!
            binding.q2PulseFourth.percent = viewModel.percent4Q2.value!!
         }

    }

}