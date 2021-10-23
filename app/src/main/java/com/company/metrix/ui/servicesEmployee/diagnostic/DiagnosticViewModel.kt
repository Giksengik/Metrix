package com.company.metrix.ui.servicesEmployee.diagnostic

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.metrix.data.model.Diagnostic
import com.company.metrix.data.model.DiagnosticAnswer
import com.company.metrix.data.model.User
import com.company.metrix.data.repository.DiagnosticRepository
import com.company.metrix.data.repository.UserRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DiagnosticViewModel @Inject constructor(
    private val diagnosticRepo: DiagnosticRepository,
    private val userRepo: UserRepository
) : ViewModel() {

    val currentUser: MutableLiveData<User> = MutableLiveData<User>()
    val users: MutableLiveData<List<User>> = MutableLiveData<List<User>>()

    suspend fun getTeamMembers() {
        currentUser.value = userRepo.getUserByEmail(Firebase.auth.currentUser?.email!!)
        users.value = userRepo.getUsersByTeam(currentUser.value!!.teamId)
    }

    suspend fun addAnswer(answer: DiagnosticAnswer){
        currentUser.value = userRepo.getUserByEmail(Firebase.auth.currentUser?.email!!)
        diagnosticRepo.addAnswer(
           answer
        )
    }

    suspend fun addDiagnostic(diag: Diagnostic) {
        currentUser.value = userRepo.getUserByEmail(Firebase.auth.currentUser?.email!!)

        diagnosticRepo.addDiagnostic(
            Diagnostic(
                team_id = currentUser.value!!.teamId,
                question_id = diag.question_id,
                value = diag.value
            )
        )
    }
}