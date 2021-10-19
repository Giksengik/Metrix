package com.company.metrix.ui.servicesEmployee.rating

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.company.metrix.BackButtonHandler
import com.company.metrix.R
import com.company.metrix.databinding.FragmentRatingBinding
import com.company.metrix.databinding.FragmentStrengthsBinding
import com.company.metrix.ui.servicesEmployee.strenghts.FragmentStrengthsDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentRating : Fragment(), BackButtonHandler {
    private val ratingViewModel: RatingViewModel by viewModels()

    private var _binding: FragmentRatingBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ratingViewModel.apply {
            viewModelScope.launch {
                calculateRating()
            }
        }

        requireActivity()
            .onBackPressedDispatcher
            .addCallback(this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (isEnabled) {
                        isEnabled = false
                        val action = FragmentRatingDirections.actionFragmentRatingToServiceFragment()
                        findNavController().navigate(action)
                    }
                }
            }
            )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRatingBinding.inflate(inflater)
        //loadData()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        ratingViewModel.rating.observe(viewLifecycleOwner, {
            ratingViewModel.rating.value?.let { it1 -> updateRatingView(it1) }
        })
        setupOnBackButtonPressed()

    }

    override fun setupOnBackButtonPressed() {
        binding.backButton.setOnClickListener {
            val action = FragmentRatingDirections.actionFragmentRatingToServiceFragment()
            findNavController().navigate(action)
        }
    }

//    private fun loadData() {
//        val user = Firebase.auth.currentUser!!
//        Firebase.database.reference.child("users").orderByChild("id").equalTo(user.email)
//            .addListenerForSingleValueEvent(object :
//                ValueEventListener {
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    if (snapshot.children.iterator().hasNext()) {
//                        val ratings: MutableList<Double> =
//                            snapshot.children.iterator().next().child("ratings")
//                                .getValue<MutableList<Double>>() ?: mutableListOf()
//                        updateRatingView(ratings)
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

    @SuppressLint("SetTextI18n")
    private fun updateRatingView(ratings: List<Double>) {
        val average = ratings.average()
        binding.ratingBar.rating = average.toFloat()
        binding.averageRating.text = if (average.isNaN()) "" else String.format("%.02f", average)
        binding.ratingCount.text = getString(R.string.estimates_count) + " " + (ratings.size-1)
        binding.loadingBar.visibility = View.INVISIBLE
        binding.ratingContent.visibility = View.VISIBLE
    }
}