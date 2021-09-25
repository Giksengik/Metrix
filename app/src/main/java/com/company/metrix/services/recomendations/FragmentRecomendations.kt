package com.company.metrix.services.recomendations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.company.metrix.databinding.FragmentRecomendationsBinding

class FragmentRecomendations : Fragment() {
    private var binding : FragmentRecomendationsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecomendationsBinding.inflate(inflater)
        return binding?.root
    }
}