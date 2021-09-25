package com.company.metrix

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.company.metrix.databinding.FragmentServicesBinding


class PulseFragment : Fragment() {
    private var binding : FragmentServicesBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentServicesBinding.inflate(inflater)
        return binding?.root
    }

}