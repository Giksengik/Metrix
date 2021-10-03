package com.company.metrix.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.company.metrix.databinding.FragmentProfileBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ProfileFragment : Fragment() {

    private lateinit var binding : FragmentProfileBinding
    private var softSkillAdapter : SkillsListAdapter? = null
    private var hardSkillAdapter : SkillsListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupList()
        setupDummyData()
    }

    private fun setupDummyData() {
        val user = Firebase.auth.currentUser!!
        binding.employeeProfileName.text = user.displayName
        binding.employeeProfileCompany.text = user.email
        binding.postInCompanyValue.text = " Сотрудник"
        binding.timeInCompanyValue.text = " 27.09.2021"
        softSkillAdapter?.submitList(
            listOf(
                "Дружелюбный",
                "Вежливый"
            )
        )
        hardSkillAdapter?.submitList(
            listOf(
                "Dagger, RxJava",
                "Проектирование систем"
            )
        )
    }

    private fun setupList() {
        /*hardSkillAdapter = SkillsListAdapter()
        softSkillAdapter = SkillsListAdapter()
        binding?.hardSkillsList?.apply{
            layoutManager = LinearLayoutManager(activity)
            adapter = hardSkillAdapter
        }
        binding?.softSkillsList?.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = softSkillAdapter
        }*/
    }
}