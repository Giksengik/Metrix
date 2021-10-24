package com.company.metrix.ui.servicesEmployer.listEmployee


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.metrix.data.model.Team
import com.company.metrix.data.model.User
import com.company.metrix.data.repository.UserRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EmployeeListViewModel @Inject constructor(val userRepo: UserRepository) : ViewModel() {
    val allUsers: MutableLiveData<List<User>> = MutableLiveData<List<User>>()
    val teams: MutableLiveData<List<Team>> = MutableLiveData<List<Team>>()
    private val currentUser: MutableLiveData<User> = MutableLiveData<User>()

    suspend fun initial() {
        currentUser.value = Firebase.auth.currentUser?.email?.let { userRepo.getUserByEmail(it) }!!
        updateValues()
    }


    suspend fun getTeamNameByTeamId(teamId : Long, companyName : String) : Team{
        return userRepo.getTeamByTeamAndCompany(teamId, companyName)
    }

    private suspend fun updateValues() {
        allUsers.value = userRepo.getAllUsersByCompany(currentUser.value!!.companyName)

        teams.value = arrayListOf()
        teams.value = userRepo.getAllTeamsByCompany(currentUser.value!!.companyName)
    }

    suspend fun removeUser(user: User) {
        userRepo.deleteUser(user)
        updateValues()
    }
}