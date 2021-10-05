package com.company.metrix.ui.services.team

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.metrix.data.model.User
import com.company.metrix.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TeamViewModel @Inject constructor(val userRepo: UserRepository): ViewModel() {

    val team : MutableLiveData<List<User>> =  MutableLiveData<List<User>>()

    suspend fun getMembersOfTeam(bossId : Long){
        val teamId = userRepo.getUserById(bossId).team_id
        team.value = userRepo.getUsersByTeam(teamId)
        Log.d("test_test", "getMembersOfTeam: team id : ${teamId} team : ${userRepo.getUserById(teamId)} ")
    }
}