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

    val percent1Q1: MutableLiveData<Int> = MutableLiveData<Int>()
    val percent2Q1: MutableLiveData<Int> = MutableLiveData<Int>()
    val percent3Q1: MutableLiveData<Int> = MutableLiveData<Int>()
    val percent4Q1: MutableLiveData<Int> = MutableLiveData<Int>()

    val percent1Q2: MutableLiveData<Int> = MutableLiveData<Int>()
    val percent2Q2: MutableLiveData<Int> = MutableLiveData<Int>()
    val percent3Q2: MutableLiveData<Int> = MutableLiveData<Int>()
    val percent4Q2: MutableLiveData<Int> = MutableLiveData<Int>()

    suspend fun getTeamPulseResults(teamId: Long) {
        val user = userRepo.getUserByEmail(Firebase.auth.currentUser?.email!!)
        val results1 = pulseRepo.getPulseByCompanyAndIdQuestion(user.companyName, 1, teamId)
        val results2 = pulseRepo.getPulseByCompanyAndIdQuestion(user.companyName, 1, teamId)

        val totalRes1 = results1.votesOne + results1.votesTwo + results1.votesThree + results1.votesFour
        val totalRes2 = results2.votesOne + results2.votesTwo + results2.votesThree + results2.votesFour

        if (totalRes1 != 0) {
            percent1Q1.value = results1.votesOne * 100 / totalRes1
            percent2Q1.value = results1.votesTwo * 100 / totalRes1
            percent3Q1.value = results1.votesThree * 100 / totalRes1
            percent4Q1.value = results1.votesFour * 100 / totalRes1
        }

        if (totalRes2 != 0) {
            percent1Q2.value = results1.votesOne * 100 / totalRes2
            percent2Q2.value = results1.votesTwo * 100 / totalRes2
            percent3Q2.value = results1.votesThree * 100 / totalRes2
            percent4Q2.value = results1.votesFour * 100 / totalRes2
        }

    }
}