package com.company.metrix.ui.servicesEmployee.recomendations

import android.os.Bundle
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
import com.company.metrix.databinding.FragmentRecomendationsBinding
import com.company.metrix.ui.servicesEmployee.strenghts.CharacteristicListAdapter
import com.company.metrix.ui.servicesEmployee.strenghts.StrengthViewModel
import com.company.metrix.ui.support.setupNavigation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentRecomendations : Fragment(), BackButtonHandler {

    private val viewModel: StrengthViewModel by viewModels()

    private var _binding: FragmentRecomendationsBinding? = null
    private val binding get() = _binding!!

    private var weaknessAdapter: CharacteristicListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.viewModelScope.launch {
            viewModel.getNegativeFeedback()
        }

        requireActivity().setupNavigation(this, FragmentRecomendationsDirections.actionFragmentRecomendationsToServiceFragment())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecomendationsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupStrengthsList()
        observeViewModel()
        setupOnBackButtonPressed()
    }

    private fun observeViewModel() {
        viewModel.apply {
            estimations.observe(viewLifecycleOwner, {
                if (it.isEmpty()) {
                    binding.emptyView.visibility = View.VISIBLE
                } else {
                    binding.emptyView.visibility = View.INVISIBLE
                    weaknessAdapter?.submitList(estimations.value)
                }
                binding.loadingBar.visibility = View.INVISIBLE
                binding.recommendationContent.visibility = View.VISIBLE
            })
        }
    }

    private fun setupStrengthsList() {
        weaknessAdapter = CharacteristicListAdapter()
        binding.weaknessList.apply {
            adapter = weaknessAdapter
            layoutManager = LinearLayoutManager(activity)
        }
        //  loadData()
    }

//
//    private fun loadData() {
//        val user = Firebase.auth.currentUser!!
//        Firebase.database.reference.child("users").orderByChild("id").equalTo(user.email)
//            .addListenerForSingleValueEvent(object :
//                ValueEventListener {
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    if (snapshot.children.iterator().hasNext()) {
//                        val comments: MutableList<String> =
//                            snapshot.children.iterator().next().child("comments")
//                                .getValue<MutableList<String>>() ?: mutableListOf()
//                        val list = comments.map {
//                            CharacteristicInfo(text = it, emoji = "\uD83D\uDCAC")
//                        }
//                        if (list.isNotEmpty()) {
//                            weaknessAdapter?.submitList(list)
//                        } else {
//                            binding.emptyView.visibility = View.VISIBLE
//                        }
//                        binding.loadingBar.visibility = View.INVISIBLE
//                        binding.recommendationContent.visibility = View.VISIBLE
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

    override fun setupOnBackButtonPressed() {
        binding.backButton.setOnClickListener {
            val action = FragmentRecomendationsDirections.actionFragmentRecomendationsToServiceFragment()
            findNavController().navigate(action)
        }
    }
}