package com.company.metrix.ui.servicesEmployee.rating

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.company.metrix.BackButtonHandler
import com.company.metrix.R
import com.company.metrix.databinding.FragmentRatingBinding
import com.company.metrix.ui.support.setupNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentRating : Fragment(), BackButtonHandler {
    private val ratingViewModel: RatingViewModel by viewModels()

    private var _binding: FragmentRatingBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().setupNavigation(this, FragmentRatingDirections.actionFragmentRatingToServiceFragment())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRatingBinding.inflate(inflater)
        ratingViewModel.allData.observe(viewLifecycleOwner) { list ->
            updateRatingView(list)
        }
        ratingViewModel.loadData()
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

    @SuppressLint("SetTextI18n")
    private fun updateRatingView(ratings: List<Double>) {
        val average = ratings.average()
        binding.ratingBar.rating = average.toFloat()
        binding.averageRating.text = if (average.isNaN()) "0.0" else String.format("%.01f", average)
        binding.ratingCount.text = getString(R.string.estimates_count) + " " + ratings.size
        binding.loadingBar.visibility = View.INVISIBLE
        binding.ratingContent.visibility = View.VISIBLE
    }
}