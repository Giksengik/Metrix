package com.company.metrix.ui.servicesEmployer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.company.metrix.databinding.FragmentServicesBinding
import com.company.metrix.ui.servicesEmployee.ServiceListAdapter
import com.company.metrix.ui.servicesEmployee.ServiceType
import com.company.metrix.ui.servicesEmployee.ServiceViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RulerServicesFragment : Fragment(), ServiceListAdapter.OnServiceClickListener {

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
                ServiceType.TEAM, ServiceType.DIAGNOSTIC,
                ServiceType.USER, ServiceType.PULSE
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
            RulerServicesFragmentDirections.actionRulerServicesFragmentToPulseResultChoiceFragment()
        )
    }

    private fun showEmployeeService() {
        findNavController().navigate(
            RulerServicesFragmentDirections.actionRulerServicesFragmentToEmployeeListFragment()
        )
    }

    private fun showTeamService() {
        findNavController().navigate(
            RulerServicesFragmentDirections.actionRulerServicesFragmentToEmployeeTeamChoiceFragment()
        )
    }

    private fun showStrengthsService() {
        //TODO
//        findNavController().navigate(
//            ServicesFragmentDirections.actionServiceFragmentToFragmentStrengths()
//        )
    }

    private fun showRecomendationsService() {
        //TODO
//        findNavController().navigate(
//            ServicesFragmentDirections.actionServiceFragmentToFragmentRecomendations()
//        )
    }

    private fun showRatingsService() {
        //TODO
//        findNavController().navigate(
//            ServicesFragmentDirections.actionServiceFragmentToFragmentRating()
//        )
    }

    private fun showDiagnosticService() {
        //TODO
//        findNavController().navigate(
//            ServicesFragmentDirections.actionServiceFragmentToFragmentDiagnostic()
//        )
    }

    private fun showAwardsService() {
        //TODO
//        findNavController().navigate(
//            ServicesFragmentDirections.actionServiceFragmentToFragmentAwards()
//        )
    }

}