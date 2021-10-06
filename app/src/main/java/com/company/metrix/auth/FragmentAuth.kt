package com.company.metrix.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.company.metrix.R
import com.company.metrix.data.model.User
import com.company.metrix.databinding.FragmentAuthBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentAuth : Fragment(), AuthHandler {

    val viewModel: AuthViewModel by viewModels()

    lateinit var binding: FragmentAuthBinding
    private lateinit var authPagesAdapter: AuthPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("test_test", "onCreate: AUTH")

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViewPager()
    }

    private fun setupViewPager() {
        activity?.let {
            val tabNames = resources.getStringArray(R.array.auth_pages)
            authPagesAdapter = AuthPagerAdapter(it, this)

            binding.authViewPager.let { viewPager ->
                viewPager.adapter = authPagesAdapter

                // если пэйджер не нулл, то и табс не должны быть нулл
                TabLayoutMediator(binding.authTabs, viewPager) { tab, position ->
                    tab.text = tabNames[position]
                }.attach()
            }
        }
    }

    override fun handleSuccessAuth() {
        viewModel.apply {
            viewModelScope.launch {
                val user = Firebase.auth.currentUser!!
                Log.d("test_test", "onCreate2222: ${user.photoUrl} ")
                initial(user.displayName, user.phoneNumber)
                Log.d("test_test", "onCreate--------: ${user.displayName} ${user.phoneNumber}")
            }
        }

        navigateToMain()
    }

    private fun navigateToMain() {
        Log.d("test_test", "onCreate: THERE")

        viewModel.viewModelScope.launch {
            Log.d("test_test", "onCreate: !!!!")

            viewModel.getCurrentUser(Firebase.auth.currentUser?.email!!)
            Log.d("test_test", "navigateToMain: ${viewModel.currentUser.value!!}")
            findNavController().navigate(
                FragmentAuthDirections.actionFragmentAuthToMainFragment(viewModel.currentUser.value!!.position)
            )
        }
        Log.d("test_test", "onCreate: :(")

    }
}