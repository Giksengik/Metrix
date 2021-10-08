package com.company.metrix.ui.servicesEmployer.pulseResults

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.company.metrix.R
import com.company.metrix.databinding.FragmentPulseResultBinding
import com.company.metrix.databinding.FragmentServicesBinding
import com.company.metrix.ui.servicesEmployer.pulseResults.pulseRecycler.PulseTeamAdapter
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PulseResultChoiceFragment : Fragment() {
    private lateinit var adapter: PulseTeamAdapter
    private val viewModel: PulseViewModel by viewModels()

    private var _binding: FragmentPulseResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.viewModelScope.launch {
            viewModel.getTeamsOfUser(Firebase.auth.currentUser!!.email ?: "monsterglad12@gmail.com")
        }
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

        init()

        viewModel.teamList.observe(viewLifecycleOwner, {
            adapter.submitList(viewModel.teamList.value)
            binding.teamsList.adapter = adapter
        })
    }

    private fun init() {
        binding.strengthsContent.visibility = View.VISIBLE
        binding.loadingBar.visibility = View.GONE
        adapter = PulseTeamAdapter()
    }

}