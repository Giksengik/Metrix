package com.company.metrix.ui.services.strenghts

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.metrix.data.model.Estimation
import com.company.metrix.data.repository.EstimationRepository
import com.company.metrix.data.stub.EstimationFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StrengthViewModel @Inject constructor(val estimationRepo: EstimationRepository): ViewModel() {
    val estimations: MutableLiveData<List<Estimation>> = MutableLiveData<List<Estimation>>()

    suspend fun initial(){
        //Stub!
        val v = EstimationFactory().getAllEstimations()
        for (i in v){
            estimationRepo.addEstimation(i)
        }
    }

    suspend fun getUserEstimations(id : Long) {
        estimations.value =  estimationRepo.getEstimationsByUserId(id)
        Log.d("Check_strength", "getUserEstimations!: ${estimationRepo.getAllEstimations()}")
    }

}