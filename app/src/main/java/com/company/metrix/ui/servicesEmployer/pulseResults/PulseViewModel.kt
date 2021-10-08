package com.company.metrix.ui.servicesEmployer.pulseResults

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.metrix.data.model.Team
import com.company.metrix.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PulseViewModel @Inject constructor(val userRepo: UserRepository) : ViewModel() {
    val teamList: MutableLiveData<Map<String, Int>> = MutableLiveData<Map<String, Int>>()

    suspend fun getTeamsOfCompany(companyName: String) {
        val map = HashMap<String, Int>()
        val allTeams: List<Team> = userRepo.getAllTeamsByCompany(companyName)

        for (team in allTeams) {
            val usersInTeam = userRepo.getUsersByTeam(team.team_id)
            map[team.team_name] = usersInTeam.size
        }

        teamList.value = map
    }

}