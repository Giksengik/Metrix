package com.company.metrix.ui.servicesEmployer.pulseResults

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.company.metrix.R
import com.company.metrix.databinding.FragmentPulseResultBinding
import com.company.metrix.databinding.FragmentServicesBinding


class PulseResultChoiceFragment : Fragment() {

    private var _binding: FragmentPulseResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPulseResultBinding.inflate(layoutInflater)
        return binding.root
    }

}