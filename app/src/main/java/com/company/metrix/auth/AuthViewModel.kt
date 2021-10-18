package com.company.metrix.auth

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.metrix.data.model.User
import com.company.metrix.data.repository.EstimationRepository
import com.company.metrix.data.repository.UserRepository
import com.company.metrix.data.stub.EmployeeFactory
import com.company.metrix.data.stub.EstimationFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userRepo: UserRepository,
    private val estimationRepo: EstimationRepository
) : ViewModel() {
    val currentUser: MutableLiveData<User> = MutableLiveData<User>()

    suspend fun initial(userName: String?, userEmail: String?) { //TODO user : User <- server
        val user1 = User(
            id = 1,
            name = userName ?: "Yana",
            email = userEmail ?: "monsterglad12@gmail.com",
            teamId = 1,
            position = "Сотрудник",
            role = "Помощник помощника стажера",
            companyName = "Tinkoff"
        )

        currentUser.value = user1
        userRepo.addUser(user1)
        Log.d("test_test", "getEmployeeInfo hhey: ${userRepo.getUserByEmail("monsterglad12@gmail.com")}")

        val factory = EmployeeFactory()
        val usrs = factory.getAllEmployees()
        val temas = factory.getAllTeams()

        for (i in usrs)
            userRepo.addUser(i)

        for (i in temas)
            userRepo.addTeam(i)

        //Stub!
        val v = EstimationFactory().getAllEstimations()
        for (i in v) {
            estimationRepo.addEstimation(i)
        }
    }

    suspend fun getCurrentUser(userEmail: String) {
        currentUser.value = userRepo.getUserByEmail(userEmail)
    }
}