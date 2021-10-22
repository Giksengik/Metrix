package com.company.metrix.ui.servicesEmployer.pulseResults

import androidx.lifecycle.ViewModel
import com.company.metrix.data.repository.PulseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PulseResultViewModel @Inject constructor(private val pulseRepo : PulseRepository) : ViewModel() {

    fun doSomething(){

    }
}