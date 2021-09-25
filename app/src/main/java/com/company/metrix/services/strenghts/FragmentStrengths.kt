package com.company.metrix.services.strenghts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.company.metrix.databinding.FragmentRecomendationsBinding
import com.company.metrix.databinding.FragmentStrengthsBinding

class FragmentStrengths : Fragment() {
    private var binding : FragmentStrengthsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStrengthsBinding.inflate(inflater)
        return binding?.root
    }
}