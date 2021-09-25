package com.company.metrix.services.rating

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.company.metrix.BackButtonHandler
import com.company.metrix.databinding.FragmentRatingBinding

class FragmentRating : Fragment(), BackButtonHandler {
    private var binding : FragmentRatingBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRatingBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupOnBackButtonPressed()
    }

    override fun setupOnBackButtonPressed() {
        binding?.backButton?.setOnClickListener{
            activity?.onBackPressed()
        }
    }
}