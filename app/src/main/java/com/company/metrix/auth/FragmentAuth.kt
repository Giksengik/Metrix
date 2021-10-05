package com.company.metrix.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.company.metrix.R
import com.company.metrix.databinding.FragmentAuthBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentAuth : Fragment() , AuthHandler {

    val viewModel : AuthViewModel by viewModels()

    var binding : FragmentAuthBinding? = null
    private lateinit var authPagesAdapter : AuthPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViewPager()
    }

    private fun setupViewPager(){
        activity?.let {
            val tabNames = resources.getStringArray(R.array.auth_pages)
            authPagesAdapter = AuthPagerAdapter(it, this)
            binding?.authViewPager?.let{ viewPager ->
                viewPager.adapter = authPagesAdapter
                // если пэйджер не нулл, то и табс не должны быть нулл
                TabLayoutMediator(binding!!.authTabs,viewPager){ tab, position ->
                    tab.text = tabNames[position]
                }.attach()
            }
        }
    }

    override fun handleSuccessAuth() {
        navigateToMain()
    }

    private fun navigateToMain() {
        findNavController().navigate(
            FragmentAuthDirections.actionFragmentAuthToMainFragment()
        )
    }
}