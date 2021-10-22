package com.company.metrix.ui.servicesEmployee.strenghts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.company.metrix.BackButtonHandler
import com.company.metrix.R
import com.company.metrix.data.model.CharacteristicInfo
import com.company.metrix.data.model.LoadingState
import com.company.metrix.databinding.FragmentStrengthsBinding
import com.company.metrix.ui.support.setupNavigation
import com.google.firebase.database.DatabaseReference
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentStrengths : Fragment(), BackButtonHandler {

    private val viewModel: StrengthViewModel by viewModels()

    private var _binding: FragmentStrengthsBinding? = null
    private val binding get() = _binding!!

    private var strengthsAdapter: CharacteristicListAdapter? = null
    private lateinit var characteristicsDatabase: DatabaseReference
    private lateinit var strengthsList: List<CharacteristicInfo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().setupNavigation(this, FragmentStrengthsDirections.actionFragmentStrengthsToServiceFragment())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStrengthsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //binding.employeeProfileName.text = user.displayName
        setupOnBackButtonPressed()
        setupStrengthsList()
        observeViewModel()

        viewModel.loadSkills()
    }

    private fun observeViewModel() {
        viewModel.loadingState.observe(viewLifecycleOwner) {
            when (it) {
                LoadingState.RECEIVING_SUCCESS -> viewModel.viewModelScope.launch {
                    viewModel.getPositiveFeedback()
                }
                else -> binding.emptyView.visibility = View.VISIBLE
            }
        }

        viewModel.estimations.observe(viewLifecycleOwner, {
            if (it.isEmpty()) {
                binding.emptyView.visibility = View.VISIBLE
                binding.loadingBar.visibility = View.INVISIBLE
            } else {
                Log.d("test_test", "onViewCreated: ${viewModel.estimations.value}")
                strengthsAdapter?.submitList(viewModel.estimations.value)
                binding.strengthsContent.visibility = View.VISIBLE
                binding.loadingBar.visibility = View.INVISIBLE
                binding.emptyView.visibility = View.INVISIBLE
            }

        })
    }

    /*private fun loadSkills() {
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
                //  loadUserData()
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
    }*/

//    private fun loadUserData() {
//        val user = Firebase.auth.currentUser!!
//        Firebase.database.reference.child("users").orderByChild("id").equalTo(user.email)
//            .addListenerForSingleValueEvent(object :
//                ValueEventListener {
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    if (snapshot.children.iterator().hasNext()) {
//                        val strengths: MutableList<String> =
//                            snapshot.children.iterator().next().child("strongSkills")
//                                .getValue<MutableList<String>>() ?: mutableListOf()
//                        updateStrengthsTop(strengths)
//                        binding.loadingBar.visibility = View.INVISIBLE
//                    } else {
//                        showErrorToast()
//                    }
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//                    showErrorToast()
//                }
//            })
//    }

    private fun showErrorToast() {
        Toast.makeText(
            requireContext(),
            getString(R.string.database_error),
            Toast.LENGTH_SHORT
        ).show()
    }

//    private fun updateStrengthsTop(strengths: MutableList<String>) {
//        val topStrengths = mutableListOf<CharacteristicInfo>()
//        val sortedStrengths = strengths.sortedByDescending { it }.distinct().reversed()
//        var i = 0
//        while (topStrengths.size < 3 && i < sortedStrengths.size) {
//            val characteristic = strengthsList.find { it.id == sortedStrengths[i] }
//            if (characteristic != null) topStrengths.add(characteristic)
//            i += 1
//        }
//        if (topStrengths.size > 0) {
//            strengthsAdapter?.submitList(topStrengths)
//        } else {
//            binding.emptyView.visibility = View.VISIBLE
//        }
//        binding.strengthsContent.visibility = View.VISIBLE
//        binding.loadingBar.visibility = View.INVISIBLE
//    }

    private fun setupStrengthsList() {
        strengthsAdapter = CharacteristicListAdapter()
        binding.strengthsList.apply {
            adapter = strengthsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    override fun setupOnBackButtonPressed() {
        binding.backButton.setOnClickListener {
            val action = FragmentStrengthsDirections.actionFragmentStrengthsToServiceFragment()
            findNavController().navigate(action)
        }
    }

}