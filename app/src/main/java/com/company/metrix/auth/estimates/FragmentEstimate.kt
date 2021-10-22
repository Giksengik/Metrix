package com.company.metrix.auth.estimates

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar.OnRatingBarChangeListener
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.company.metrix.R
import com.company.metrix.auth.AuthHandler
import com.company.metrix.data.model.LoadingState
import com.company.metrix.databinding.FragmentEstimateBinding
import com.company.metrix.ui.servicesEmployee.CharacteristicAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

enum class Skills(val skillName: String) {
    POLITE("Вежливость"), MOBILE("Мобильность"), PROF("Профессионализм"),
    SPEED("Скорость"), FRIENDLY("Дружелюбность")
}

@AndroidEntryPoint
class FragmentEstimate() : Fragment() {

    private val viewModel: EstimateViewModel by viewModels()
    private val characteristicsList = mutableListOf<String>()

    companion object {
        fun newInstance(authHandler: AuthHandler): Fragment {
            return FragmentEstimate(authHandler)
        }
    }

    private constructor(authHandler: AuthHandler) : this() {
        this.authHandler = authHandler
    }

    private var authHandler: AuthHandler? = null
    private lateinit var binding: FragmentEstimateBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEstimateBinding.inflate(inflater)

        val adapter = CharacteristicAdapter(mutableListOf()) { id, isSelected ->
            if (isSelected) {
                if (id in characteristicsList) characteristicsList.remove(id)
            } else {
                Log.d("test_test", "onCreateView: ${id}")
                characteristicsList.add(Skills.values()[id.toInt()].skillName)
            }
        }
        binding.strengthsView.adapter = adapter

        binding.buttonConfirmEstimate.setOnClickListener { onSubmit() }

        binding.ratingBar.onRatingBarChangeListener =
            OnRatingBarChangeListener { _, rating, _ ->
                if (rating <= 0.5) {
                    binding.ratingBar.rating = 1.0f
                }
            }

        binding.sentButton.setOnClickListener {
            binding.sentButton.visibility = View.INVISIBLE
            binding.sentEmoji.visibility = View.INVISIBLE
            binding.sentTitle.visibility = View.INVISIBLE
            binding.loadingBackground.visibility = View.INVISIBLE
            binding.buttonConfirmEstimate.visibility = View.VISIBLE
        }

        viewModel.loadingState.observe(viewLifecycleOwner) { loadingState ->
            when (loadingState) {
                LoadingState.LOADING -> showLoading()
                LoadingState.RECEIVING_SUCCESS -> {
                    binding.buttonConfirmEstimate.visibility = View.VISIBLE
                    hideLoading()
                }
                LoadingState.RECEIVING_ERROR -> {
                    hideLoading()
                    showErrorToast()
                }
                LoadingState.SENDING_SUCCESS -> {
                    binding.loadingBar.visibility = View.INVISIBLE
                    onSent()
                }
                LoadingState.SENDING_ERROR -> {
                    hideLoading()
                    showError()
                }
                else -> {
                    hideLoading()
                    showErrorToast()
                }
            }
        }

        viewModel.allData.observe(viewLifecycleOwner) { list ->
            adapter.setData(list)
        }

        viewModel.loadData()

        return binding.root
    }

    private fun onSubmit() {
        hideError()

        val userId = binding.employeeIdField.text.toString().trim()
        val userRating = binding.ratingBar.rating.toDouble()
        val userComment = binding.employeeCommentField.text.toString().trim()
        sendFeedback(userId, userComment, userRating)
    }

    private fun onSent() {
        binding.sentButton.visibility = View.VISIBLE
        binding.sentEmoji.visibility = View.VISIBLE
        binding.sentTitle.visibility = View.VISIBLE
        binding.loadingBar.visibility = View.INVISIBLE
    }

    private fun hideError() {
        binding.employeeIdBlock.error = ""
    }

    private fun showError() {
        binding.employeeIdBlock.error = getString(R.string.id_not_found)
        hideLoading()
    }

    private fun showErrorToast() {
        Toast.makeText(
            requireContext(),
            getString(R.string.database_error),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun sendFeedback(userId: String, userComment: String, userRating: Double) {
        viewModel.viewModelScope.launch {
            viewModel.sendFeedback(userId, characteristicsList, userComment, userRating)
        }
    }

    private fun showLoading() {
        binding.loadingBackground.visibility = View.VISIBLE
        binding.loadingBar.visibility = View.VISIBLE
        binding.buttonConfirmEstimate.visibility = View.INVISIBLE
    }

    private fun hideLoading() {
        binding.loadingBackground.visibility = View.INVISIBLE
        binding.loadingBar.visibility = View.INVISIBLE
        binding.buttonConfirmEstimate.visibility = View.VISIBLE
    }

}