package com.company.metrix.services.awards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.company.metrix.databinding.FragmentAwardsBinding
import com.company.metrix.databinding.FragmentMainBinding

class FragmentAwards : Fragment() {
    private var binding : FragmentAwardsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAwardsBinding.inflate(inflater)
        return binding?.root
    }
}