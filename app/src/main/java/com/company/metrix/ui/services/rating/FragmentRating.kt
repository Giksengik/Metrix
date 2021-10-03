package com.company.metrix.ui.services.rating

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.company.metrix.BackButtonHandler
import com.company.metrix.R
import com.company.metrix.databinding.FragmentRatingBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentRating : Fragment(), BackButtonHandler {
    private lateinit var binding: FragmentRatingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRatingBinding.inflate(inflater)
        loadData()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupOnBackButtonPressed()
    }

    override fun setupOnBackButtonPressed() {
        binding.backButton.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun loadData() {
        val user = Firebase.auth.currentUser!!
        Firebase.database.reference.child("users").orderByChild("id").equalTo(user.email)
            .addListenerForSingleValueEvent(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.children.iterator().hasNext()) {
                        val ratings: MutableList<Double> =
                            snapshot.children.iterator().next().child("ratings")
                                .getValue<MutableList<Double>>() ?: mutableListOf()
                        updateRatingView(ratings)
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

    @SuppressLint("SetTextI18n")
    private fun updateRatingView(ratings: List<Double>) {
        val average = ratings.average()
        binding.ratingBar.rating = average.toFloat()
        binding.averageRating.text = if (average.isNaN()) "" else String.format("%.02f", average)
        binding.ratingCount.text = getString(R.string.estimates_count) + " " + ratings.size
        binding.loadingBar.visibility = View.INVISIBLE
        binding.ratingContent.visibility = View.VISIBLE
    }
}