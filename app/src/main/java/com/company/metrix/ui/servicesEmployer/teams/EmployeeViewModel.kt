package com.company.metrix.ui.servicesEmployer.teams

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.metrix.data.model.Estimation
import com.company.metrix.data.model.User
import com.company.metrix.data.repository.EstimationRepository
import com.company.metrix.data.repository.UserRepository
import com.company.metrix.ui.servicesEmployer.teamRecycler.TeamModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EmployeeViewModel @Inject constructor(
    private val userRepo: UserRepository,
    private val estimateRepo: EstimationRepository
) : ViewModel() {

    val userList: MutableLiveData<List<User>> = MutableLiveData<List<User>>()
    val companyName: MutableLiveData<String> = MutableLiveData<String>()
    val teamList: MutableLiveData<List<String>> = MutableLiveData<List<String>>()
    val currentUser: MutableLiveData<User> = MutableLiveData<User>()
    val userRatings: MutableLiveData<MutableList<MutableList<Estimation>>> =
        MutableLiveData<MutableList<MutableList<Estimation>>>()

    init {
        userRatings.value = arrayListOf()
//        for(i in userRatings.value!!.indices){
//            userRatings.value!![i] = arrayListOf()
//        }
    }

    suspend fun calculateRatings() {
        val users = userRepo.getAllUsers()
        val userIds: MutableSet<Long> = mutableSetOf()
        for (userId in users) {
            userIds.add(userId.id)
        }
        for (id in userIds) {
            val list = estimateRepo.getEstimationsByUserId(id)
            val rate: MutableList<Estimation> = arrayListOf()
            for (est in list)
                rate.add(est)
            userRatings.value!!.add(rate)
        }
    }

    suspend fun initial() {
        val user = Firebase.auth.currentUser?.email?.let { userRepo.getUserByEmail(it) }!!
        currentUser.value = user
        companyName.value = user.companyName
        calculateRatings()
    }

    suspend fun removeUser(user: User) {
        userRepo.deleteUser(user)
        userList.value = userRepo.getAllUsersByTeamAndCompany(user.teamId, user.companyName)
    }

    suspend fun getUsersInTeam(team: TeamModel) {
        initial()
        userList.value = userRepo.getAllUsersByTeamAndCompany(team.teamId, team.companyName)
        getTeamsOfCompany()
    }

    suspend fun getTeamsOfCompany() {
        teamList.value =
            userRepo.getAllTeamsByCompany(currentUser.value!!.companyName).map { it.team_name }
    }

    suspend fun moveUserToOtherTeam(teamName: String, userId: Long) {
        val user = userRepo.getUserById(userId)
        val savedTeamId = user.teamId
        val team = userRepo.getTeamByNameAndCompany(teamName, user.companyName)
        Log.d("test_test", "moveUserToOtherTeam: NAME $savedTeamId")
        user.teamId = team.team_id
        userRepo.updateUser(user)
        Log.d("test_test", "moveUserToOtherTeam: NAME 2 $savedTeamId")
        userList.value = userRepo.getAllUsersByTeamAndCompany(savedTeamId, user.companyName)
    }
}