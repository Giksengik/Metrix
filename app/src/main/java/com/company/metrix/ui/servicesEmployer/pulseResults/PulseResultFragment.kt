package com.company.metrix.ui.servicesEmployer.pulseResults

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.company.metrix.databinding.FragmentPulseResultBinding
import com.company.metrix.ui.servicesEmployer.teamRecycler.TeamModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PulseResultFragment : Fragment() {
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

    }

}