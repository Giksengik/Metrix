package com.company.metrix.ui.pulse

import androidx.lifecycle.ViewModel
import com.company.metrix.data.model.Pulse
import com.company.metrix.data.repository.PulseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class PulseViewModel @Inject constructor(val pulseRepository: PulseRepository) : ViewModel() {

    /*suspend fun updateVotes(companyName: String, number: Int) {
        when(number){
            1-> pulseRepository.updatePulse(
                pulseRepository.getPulseByTeamId()
            )

        }
     }*/

    //TODO to initial stub
    suspend fun addPulseStatistics(pulse: Pulse) {
        pulseRepository.insertPulse(
            pulse
        )
    }

}