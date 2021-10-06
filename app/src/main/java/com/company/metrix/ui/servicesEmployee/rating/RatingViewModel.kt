package com.company.metrix.ui.servicesEmployee.rating

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.metrix.data.repository.EstimationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RatingViewModel @Inject constructor(val repository: EstimationRepository) : ViewModel() {

    val rating: MutableLiveData<List<Double>> = MutableLiveData<List<Double>>()

    suspend fun calculateRating() {
        val list = repository.getAllEstimations()
        var rate: MutableList<Double> = arrayListOf()
        for (est in list) {
            rate.add(est.rate)
        }
        rating.value = rate
    }
}