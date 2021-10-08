package com.company.metrix.ui.servicesEmployer.pulseResults

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.metrix.data.model.Team
import com.company.metrix.data.repository.UserRepository
import com.company.metrix.ui.servicesEmployer.pulseResults.pulseRecycler.PulseTeamModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PulseViewModel @Inject constructor(val userRepo: UserRepository) : ViewModel() {
    val teamList: MutableLiveData<List<PulseTeamModel>> = MutableLiveData<List<PulseTeamModel>>()

    suspend fun getTeamsOfUser(userEmail: String) {
        val user = userRepo.getUserByEmail(userEmail)
        val list = ArrayList<PulseTeamModel>()
        val allTeams: List<Team> = userRepo.getAllTeamsByCompany(user.companyName)

        for (team in allTeams) {
            val usersInTeam = userRepo.getUsersByTeam(team.team_id)
            if (usersInTeam.isNotEmpty())
                list.add(PulseTeamModel(team.team_name, usersInTeam.size))
        }

        Log.d("test_test", "getTeamsOfUser: ${list}")

        teamList.value = list
    }

}