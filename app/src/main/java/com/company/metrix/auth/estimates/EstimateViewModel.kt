package com.company.metrix.auth.estimates

import androidx.lifecycle.ViewModel
import com.company.metrix.data.model.Estimation
import com.company.metrix.data.repository.EstimationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class EstimateViewModel @Inject constructor(val estimateRepository : EstimationRepository) : ViewModel(){
    private var stubId : Any = Any();

    suspend fun sendFeedback(id : Long, list : MutableList<String>, comment : String, rate : Double){
        for (est in list){
            stubId = Any();
            estimateRepository.addEstimation(Estimation(stubId.hashCode().toLong(), comment,id,2, rate, est))
        }
    }
}