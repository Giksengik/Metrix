package com.company.metrix.ui.servicesEmployee

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
class ServiceViewModel @Inject constructor(
    private val userRepo: UserRepository,
    private val estimationRepo: EstimationRepository
) : ViewModel() {

    val mainUser: MutableLiveData<User> = MutableLiveData<User>()


}