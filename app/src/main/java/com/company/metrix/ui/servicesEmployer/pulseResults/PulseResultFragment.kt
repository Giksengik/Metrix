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

    //TODO заменить на ресайклер
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            loadingBar.visibility = View.GONE
            content.visibility = View.VISIBLE

            pulseOne.icon = R.drawable.ic_first_choice
            pulseTwo.icon = R.drawable.ic_second_choice
            pulseThree.icon = R.drawable.ic_third_choice
            pulseFour.icon = R.drawable.ic_fourth_choice

            q2PulseFirst.icon = R.drawable.ic_first_choice
            q2PulseSecond.icon = R.drawable.ic_second_choice
            q2PulseThird.icon = R.drawable.ic_third_choice
            q2PulseFourth.icon = R.drawable.ic_fourth_choice

            viewModel.percent1Q1.observe(viewLifecycleOwner)  {
                pulseOne.percent = it
            }

            viewModel.percent2Q1.observe(viewLifecycleOwner)  {
                pulseTwo.percent = it
            }

            viewModel.percent3Q1.observe(viewLifecycleOwner)  {
                pulseThree.percent = it
            }

            viewModel.percent4Q1.observe(viewLifecycleOwner)  {
                pulseFour.percent = it
            }

            viewModel.percent1Q2.observe(viewLifecycleOwner)  {
                q2PulseFirst.percent = it
            }

            viewModel.percent2Q2.observe(viewLifecycleOwner)  {
                q2PulseSecond.percent = it
            }

            viewModel.percent3Q2.observe(viewLifecycleOwner)  {
                q2PulseThird.percent = it
            }

            viewModel.percent4Q2.observe(viewLifecycleOwner)  {
                q2PulseFourth.percent = it
            }

            viewModel.apply {
                viewModelScope.launch {
                    team?.let { getTeamPulseResults(it.teamId) }

                    /*pulseOne.percent = percent1Q1.value ?: 0
                    pulseTwo.percent = percent2Q1.value ?: 0
                    pulseThree.percent = percent3Q1.value ?: 0
                    pulseFour.percent = percent4Q1.value ?: 0

                    q2PulseFirst.percent = percent1Q2.value ?: 0
                    q2PulseSecond.percent = percent2Q2.value ?: 0
                    q2PulseThird.percent = percent3Q2.value ?: 0
                    q2PulseFourth.percent = percent4Q2.value ?: 0*/
                }
            }
        }
    }

}