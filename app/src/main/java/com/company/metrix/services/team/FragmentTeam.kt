package com.company.metrix.services.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.company.metrix.databinding.FragmentStrengthsBinding
import com.company.metrix.databinding.FragmentTeamBinding

class FragmentTeam : Fragment() {
    private var binding : FragmentTeamBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTeamBinding.inflate(inflater)
        return binding?.root
    }
}