package com.company.metrix.ui.pulse

import androidx.lifecycle.ViewModel
import com.company.metrix.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class PulseViewModel @Inject constructor(val userRepository: UserRepository) : ViewModel() {

    fun addPulseStatisticsToCompany() {

    }

}