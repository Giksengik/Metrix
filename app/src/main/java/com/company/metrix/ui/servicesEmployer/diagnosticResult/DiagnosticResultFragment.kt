package com.company.metrix.ui.servicesEmployer.diagnosticResult

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.company.metrix.R
import com.company.metrix.databinding.FragmentDiagnosticResultBinding
import com.company.metrix.databinding.FragmentEmployeeListBinding


class DiagnosticResultFragment : Fragment() {

    private var _binding: FragmentDiagnosticResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDiagnosticResultBinding.inflate(layoutInflater)

        return binding.root
    }

}