package com.company.metrix.ui.services.strenghts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.metrix.data.model.Estimation
import com.company.metrix.data.repository.EstimationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StrengthViewModel @Inject constructor(val estimationRepo: EstimationRepository): ViewModel() {
    val estimations: MutableLiveData<List<Estimation>> = MutableLiveData<List<Estimation>>()

    suspend fun getUserEstimations(id : Long) {
        estimations.value =  estimationRepo.getEstimationsByUserId(id)
    }

}