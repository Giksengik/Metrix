package com.company.metrix

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.company.metrix.databinding.FragmentMainBinding


class MainFragment : Fragment() {
    private var binding : FragmentMainBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupNav()
    }


    private fun setupNav(){
        val fragment = childFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val controller = fragment.findNavController()
        binding?.bottomNav?.setupWithNavController(controller)
    }
}