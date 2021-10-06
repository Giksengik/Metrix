package com.company.metrix.ui.servicesEmployee.strenghts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.metrix.data.model.Estimation
import com.company.metrix.data.repository.EstimationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StrengthViewModel @Inject constructor(val estimationRepo: EstimationRepository) :
    ViewModel() {
    val estimations: MutableLiveData<List<Estimation>> = MutableLiveData<List<Estimation>>()


    suspend fun getPositiveFeedback(id: Long) {
        estimations.value = estimationRepo.getEstimationsByUserId(id).filter { it.rate >= 4.0 }

    }

    suspend fun getNegativeFeedback(id: Long) {
        estimations.value = estimationRepo.getEstimationsByUserId(id).filter { it.rate < 4.0 }
    }

}