package com.company.metrix.ui.servicesEmployee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.company.metrix.databinding.FragmentServicesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ServicesFragment : Fragment(), ServiceListAdapter.OnServiceClickListener {

    private var _binding: FragmentServicesBinding? = null
    private val binding get() = _binding!!

    private lateinit var serviceAdapter: ServiceListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentServicesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupServicesList()
    }

    private fun setupServicesList() {
        serviceAdapter = ServiceListAdapter(this)
        binding.service.servicesList.apply {
            adapter = serviceAdapter
            layoutManager = GridLayoutManager(
                context, 2,
                LinearLayoutManager.VERTICAL, false
            )
        }
        setupAdapterItems()
    }

    private fun setupAdapterItems() {
        //serviceAdapter.submitList(ServiceType.values().toList())
        serviceAdapter.submitList(
            arrayListOf(
                ServiceType.DIAGNOSTIC, ServiceType.RATING, ServiceType.STRENGTHS,
                ServiceType.RECOMENDATIONS, ServiceType.AWARDS, ServiceType.TEAM
            )
        )

    }

    override fun onServiceClick(serviceType: ServiceType) =
        when (serviceType) {
            ServiceType.AWARDS -> showAwardsService()
            ServiceType.DIAGNOSTIC -> showDiagnosticService()
            ServiceType.RATING -> showRatingsService()
            ServiceType.RECOMENDATIONS -> showRecomendationsService()
            ServiceType.STRENGTHS -> showStrengthsService()
            ServiceType.TEAM -> showTeamService()
            ServiceType.PULSE -> showPulseService()
            ServiceType.USER -> showEmployeeService()
        }

    private fun showPulseService() {
        findNavController().navigate(
            ServicesFragmentDirections.actionServiceFragmentToFragmentTeam()
        )
    }

    private fun showEmployeeService() {
        findNavController().navigate(
            ServicesFragmentDirections.actionServiceFragmentToFragmentStrengths()
        )
    }

    private fun showTeamService() {
        findNavController().navigate(
            ServicesFragmentDirections.actionServiceFragmentToFragmentTeam()
        )
    }

    private fun showStrengthsService() {
        findNavController().navigate(
            ServicesFragmentDirections.actionServiceFragmentToFragmentStrengths()
        )
    }

    private fun showRecomendationsService() {
        findNavController().navigate(
            ServicesFragmentDirections.actionServiceFragmentToFragmentRecomendations()
        )
    }

    private fun showRatingsService() {
        findNavController().navigate(
            ServicesFragmentDirections.actionServiceFragmentToFragmentRating()
        )
    }

    private fun showDiagnosticService() {
        findNavController().navigate(
            ServicesFragmentDirections.actionServiceFragmentToFragmentDiagnostic()
        )
    }

    private fun showAwardsService() {
        findNavController().navigate(
            ServicesFragmentDirections.actionServiceFragmentToFragmentAwards()
        )
    }

}