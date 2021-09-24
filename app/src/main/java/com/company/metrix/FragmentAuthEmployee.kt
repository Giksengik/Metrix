package com.company.metrix

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.company.metrix.databinding.FragmentEmployeeAuthBinding
import com.company.metrix.databinding.FragmentFavouriteBinding

class FragmentAuthEmployee : Fragment() {
    private var binding : FragmentEmployeeAuthBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEmployeeAuthBinding.inflate(inflater)
        return binding?.root
    }
}