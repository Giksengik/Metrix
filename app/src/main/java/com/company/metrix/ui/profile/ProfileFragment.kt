package com.company.metrix.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.company.metrix.databinding.FragmentProfileBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    val profileViewModel: ProfileViewModel by viewModels()
    private lateinit var binding: FragmentProfileBinding
    private val softSkillAdapter: SkillsListAdapter by lazy { SkillsListAdapter() }
    private val hardSkillAdapter: SkillsListAdapter by lazy { SkillsListAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater)

        profileViewModel.viewModelScope.launch {
            profileViewModel.initial();
            profileViewModel.getEmployeeInfo(1)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        profileViewModel.user.observe(viewLifecycleOwner, {
            setupDummyData()
            setupList()
        })


        profileViewModel.viewModelScope.launch {
            profileViewModel.getEmployeeInfo(1)
        }

        profileViewModel.viewModelScope.launch {
            profileViewModel.initial();
        }

        setupDummyData()
    }


    private fun setupDummyData() {
        val user = Firebase.auth.currentUser!!
        binding.employeeProfileName.text = user.displayName
        binding.employeeProfileCompany.text = user.email

        binding.authorized.titleUser.text = "Авторизован"
        binding.authorized.subtitle.text = " 27.09.2021"

        binding.position.titleUser.text = "Должность: "
        binding.position.subtitle.text = profileViewModel.user.value?.position

        softSkillAdapter.submitList(
            listOf(
                "Дружелюбный",
                "Вежливый"
            )
        )
        hardSkillAdapter.submitList(
            listOf(
                "Dagger, RxJava",
                "Проектирование систем"
            )
        )
    }

    private fun setupList() {
        binding.softSkills.adapter = softSkillAdapter
        binding.hardSkills.adapter = hardSkillAdapter
    }
}