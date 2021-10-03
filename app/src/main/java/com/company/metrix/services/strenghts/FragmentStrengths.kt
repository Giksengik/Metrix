package com.company.metrix.services.strenghts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.company.metrix.BackButtonHandler
import com.company.metrix.R
import com.company.metrix.databinding.FragmentStrengthsBinding
import com.company.metrix.model.CharacteristicInfo
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class FragmentStrengths : Fragment() , BackButtonHandler {

    private lateinit var binding : FragmentStrengthsBinding
    private var strengthsAdapter : CharacteristicListAdapter? = null
    private lateinit var characteristicsDatabase: DatabaseReference
    private lateinit var strengthsList : List<CharacteristicInfo>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStrengthsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupOnBackButtonPressed()
        setupStrengthsList()
    }

    private fun loadSkills() {
        characteristicsDatabase = Firebase.database.reference.child("characteristics")
        val characteristicsValueListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val list: MutableList<CharacteristicInfo> = ArrayList()
                for (ds in dataSnapshot.children) {
                    val characteristic: CharacteristicInfo? =
                        ds.getValue(CharacteristicInfo::class.java)
                    if (characteristic != null) list.add(characteristic)
                }
                strengthsList = list
                loadUserData()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.database_error),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        characteristicsDatabase.addListenerForSingleValueEvent(characteristicsValueListener)
    }

    private fun loadUserData() {
        val user = Firebase.auth.currentUser!!
        Firebase.database.reference.child("users").orderByChild("id").equalTo(user.email)
            .addListenerForSingleValueEvent(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.children.iterator().hasNext()) {
                        val strengths: MutableList<String> =
                            snapshot.children.iterator().next().child("strongSkills")
                                .getValue<MutableList<String>>() ?: mutableListOf()
                        updateStrengthsTop(strengths)
                        binding.loadingBar.visibility = View.INVISIBLE
                    } else {
                        showErrorToast()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    showErrorToast()
                }
            })
    }

    private fun showErrorToast() {
        Toast.makeText(
            requireContext(),
            getString(R.string.database_error),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun updateStrengthsTop(strengths: MutableList<String>) {
        val topStrengths = mutableListOf<CharacteristicInfo>()
        val sortedStrengths = strengths.sortedByDescending { it }.distinct().reversed()
        var i = 0
        while (topStrengths.size < 3 && i < sortedStrengths.size) {
            val characteristic = strengthsList.find { it.id == sortedStrengths[i] }
            if (characteristic != null) topStrengths.add(characteristic)
            i += 1
        }
        if (topStrengths.size > 0) {
            strengthsAdapter?.submitList(topStrengths)
        } else {
            binding.emptyView.visibility = View.VISIBLE
        }
        binding.strengthsContent.visibility = View.VISIBLE
        binding.loadingBar.visibility = View.INVISIBLE
    }

    private fun setupStrengthsList() {
        strengthsAdapter = CharacteristicListAdapter()
        binding.strengthsList.apply{
            adapter = strengthsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
        loadSkills()
    }

    override fun setupOnBackButtonPressed() {
        binding.backButton.setOnClickListener{
            activity?.onBackPressed()
        }
    }

}