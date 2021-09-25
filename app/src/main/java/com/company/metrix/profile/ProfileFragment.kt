package com.company.metrix.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.company.metrix.databinding.FragmentProfileBinding
import com.company.metrix.model.Employee
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ProfileFragment : Fragment() {

    private var binding : FragmentProfileBinding? = null
    private lateinit var database: DatabaseReference
    private var softSkillAdapter : SkillsListAdapter? = null
    private var hardSkillAdapter : SkillsListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater)
        database = Firebase.database.reference

        loadEmployeeToDatabase()

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupList()
        setupDummyData()
    }

    private fun setupDummyData() {
        binding?.employeeProfileName?.text = "Федор Седов"
        binding?.employeeProfileCompany?.text = "It School Samsung"
        binding?.postInCompanyValue?.text = "Босс"
        binding?.timeInCompanyValue?.text = "3 года"
        softSkillAdapter?.submitList(
            listOf(
                "Крутой",
                "Ваще крутой",
                "Вежливый"
            )
        )
        hardSkillAdapter?.submitList(
            listOf(
                "Ultra coder",
                "Проектирование систем"
            )
        )
    }

    private fun setupList() {
        hardSkillAdapter = SkillsListAdapter()
        softSkillAdapter = SkillsListAdapter()
        binding?.hardSkillsList?.apply{
            layoutManager = LinearLayoutManager(activity)
            adapter = hardSkillAdapter
        }
        binding?.softSkillsList?.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = softSkillAdapter
        }
    }

    private fun loadEmployeeToDatabase()  {
        val em = Employee(
            id = 0,
            team = 0,
            ratings = listOf(3.0, 2.0, 5.0),
            strongSkills = listOf("communication", "works_hard"),
            weakSkills = listOf("code_skill", "speed"),
            achievements = listOf("friendly")
        )
        val user = database.child(Firebase.auth.currentUser!!.uid)
        user.child("id").setValue(em.id)
        user.child("team").setValue(em.team)
        user.child("ratings").setValue(em.ratings)
        user.child("strongSkills").setValue(em.strongSkills)
        user.child("weakSkills").setValue(em.weakSkills)
        user.child("achievements").setValue(em.achievements)
    }
}