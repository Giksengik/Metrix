package com.company.metrix.services.diagnostic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.company.metrix.databinding.FragmentAwardsBinding
import com.company.metrix.databinding.FragmentDiagnosticBinding

class FragmentDiagnostic : Fragment() {
    private var binding : FragmentDiagnosticBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDiagnosticBinding.inflate(inflater)
        return binding?.root
    }
}