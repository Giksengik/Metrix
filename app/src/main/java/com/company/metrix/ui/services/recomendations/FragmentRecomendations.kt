package com.company.metrix.ui.services.recomendations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.company.metrix.BackButtonHandler
import com.company.metrix.R
import com.company.metrix.databinding.FragmentRecomendationsBinding
import com.company.metrix.data.model.CharacteristicInfo
import com.company.metrix.ui.services.strenghts.CharacteristicListAdapter
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentRecomendations : Fragment() , BackButtonHandler{

    private lateinit var binding : FragmentRecomendationsBinding
    private var weaknessAdapter : CharacteristicListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecomendationsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupStrengthsList()
        setupOnBackButtonPressed()
    }

    private fun setupStrengthsList() {
        weaknessAdapter = CharacteristicListAdapter()
        binding.weaknessList.apply{
            adapter = weaknessAdapter
            layoutManager = LinearLayoutManager(activity)
        }
        loadData()
    }

    private fun loadData() {
        val user = Firebase.auth.currentUser!!
        Firebase.database.reference.child("users").orderByChild("id").equalTo(user.email)
            .addListenerForSingleValueEvent(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.children.iterator().hasNext()) {
                        val comments: MutableList<String> =
                            snapshot.children.iterator().next().child("comments")
                                .getValue<MutableList<String>>() ?: mutableListOf()
                        val list = comments.map {
                            CharacteristicInfo(text = it, emoji = "\uD83D\uDCAC")
                        }
                        if (list.isNotEmpty()) {
                            weaknessAdapter?.submitList(list)
                        } else {
                            binding.emptyView.visibility = View.VISIBLE
                        }
                        binding.loadingBar.visibility = View.INVISIBLE
                        binding.recommendationContent.visibility = View.VISIBLE
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

    override fun setupOnBackButtonPressed() {
        binding.backButton.setOnClickListener{
            activity?.onBackPressed()
        }
    }
}