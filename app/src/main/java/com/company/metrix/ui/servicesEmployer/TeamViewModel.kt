package com.company.metrix.ui.servicesEmployer

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.metrix.data.model.Team
import com.company.metrix.data.model.User
import com.company.metrix.data.repository.UserRepository
import com.company.metrix.ui.servicesEmployer.teamRecycler.TeamModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TeamViewModel @Inject constructor(val userRepo: UserRepository) : ViewModel() {
    val teamList: MutableLiveData<List<TeamModel>> = MutableLiveData<List<TeamModel>>()
    val currentUser: MutableLiveData<User> = MutableLiveData<User>()

    suspend fun getTeamsOfUser() {
        currentUser.value =
            userRepo.getUserByEmail(Firebase.auth.currentUser?.email!!)
        val list = ArrayList<TeamModel>()
        val allTeams: List<Team> = userRepo.getAllTeamsByCompany(currentUser.value!!.companyName)

        for (team in allTeams) {
            val usersInTeam = userRepo.getAllUsersByTeamAndCompany(team.team_id, currentUser.value!!.companyName)
            Log.d("test_test", "NEW getTeamsOfUser: $usersInTeam")

            list.add(
                TeamModel(
                    teamName = team.team_name,
                    peopleCount = ", человек ${usersInTeam.size}",
                    teamId = team.team_id,
                    companyName = currentUser.value!!.companyName
                )
            )
        }

        teamList.value = list
    }

    suspend fun createNewTeam(teamModel: Team) {
        userRepo.addTeam(teamModel)
        getTeamsOfUser()
    }

}