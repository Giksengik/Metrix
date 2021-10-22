package com.company.metrix.ui.servicesEmployer.pulseResults

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.metrix.data.model.Pulse
import com.company.metrix.data.repository.PulseRepository
import com.company.metrix.data.repository.UserRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PulseResultViewModel @Inject constructor(
    private val pulseRepo: PulseRepository,
    private val userRepo: UserRepository
) : ViewModel() {

    val currenTeamPulse : MutableLiveData<Pulse> = MutableLiveData<Pulse>()

    suspend fun getTeamPulseResults(teamId: Long) {
        val user = userRepo.getUserByEmail(Firebase.auth.currentUser?.email!!)
        val results = pulseRepo.getPulseByCompanyAndIdQuestion(user.companyName, 1, teamId)
        currenTeamPulse.value = results
    }
}