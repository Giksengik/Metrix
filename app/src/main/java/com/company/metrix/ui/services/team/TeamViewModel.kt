package com.company.metrix.ui.services.team

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.metrix.data.model.User
import com.company.metrix.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TeamViewModel @Inject constructor(val userRepository: UserRepository): ViewModel() {

    val team : MutableLiveData<List<User>> =  MutableLiveData<List<User>>()

    suspend fun getMembersOfTeam(teamId : Long){
        team.value = userRepository.getUsersByTeam(teamId)
    }
}