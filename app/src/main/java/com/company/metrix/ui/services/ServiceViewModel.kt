package com.company.metrix.ui.services

import android.util.Log
import androidx.lifecycle.ViewModel
import com.company.metrix.data.model.User
import com.company.metrix.data.repository.EstimationRepository
import com.company.metrix.data.repository.UserRepository
import com.company.metrix.data.stub.EmployeeFactory
import com.company.metrix.data.stub.EstimationFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ServiceViewModel @Inject constructor(
    private val userRepo: UserRepository,
    private val estimationRepo: EstimationRepository
) : ViewModel() {

    suspend fun initial() {
        val user1 = User(
            id = 1,
            name = "Yana",
            email = "monsterglad12@gmail.com",
            team_id = 1,
            position = "Руководитель",
            role = "Тим лид гений босс"
        )

        userRepo.addUser(user1)

        val usrs = EmployeeFactory().getAllEmployees()

        for (i in usrs)
            userRepo.addUser(i)

        //Stub!
        val v = EstimationFactory().getAllEstimations()
        for (i in v) {
            estimationRepo.addEstimation(i)
        }
        Log.d("test_test", "initial!: ${estimationRepo.getAllEstimations()}")
    }
}