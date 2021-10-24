package com.company.metrix.auth

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.metrix.data.model.AuthType
import com.company.metrix.data.model.Pulse
import com.company.metrix.data.model.User
import com.company.metrix.data.repository.DiagnosticRepository
import com.company.metrix.data.repository.EstimationRepository
import com.company.metrix.data.repository.PulseRepository
import com.company.metrix.data.repository.UserRepository
import com.company.metrix.data.stub.EmployeeFactory
import com.company.metrix.data.stub.EstimationFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userRepo: UserRepository,
    private val estimationRepo: EstimationRepository,
    private val pulseRepo: PulseRepository,
    private val diagnosticRepo: DiagnosticRepository
) : ViewModel() {
    val currentUser: MutableLiveData<User> = MutableLiveData<User>()

    suspend fun initial(userName: String, userEmail: String, authType: AuthType) { //TODO user : User <- server
        val user1 = User(
            id = 1,
            name = userName,
            email = userEmail,
            teamId = 1,
            position = authType.uiName,
            role = "Помощник помощника стажера",
            companyName = "Tinkoff"
        )

        currentUser.value = user1
        userRepo.addUser(user1)

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
        var cnt = 1L

        for (team in temas) {
            pulseRepo.insertPulse(
                Pulse(
                    cnt,
                    1,
                    team.team_id,
                    team.companyName,
                    0,
                    0,
                    0,
                    0
                )
            )

            pulseRepo.insertPulse(
                Pulse(
                    cnt * 10,
                    2,
                    team.team_id,
                    team.companyName,
                    0,
                    0,
                    0,
                    0
                )
            )
            cnt++
        }
    }

    suspend fun getCurrentUser(userEmail: String) {
        currentUser.value = userRepo.getUserByEmail(userEmail)
    }
}