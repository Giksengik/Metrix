package com.company.metrix.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.metrix.data.model.Estimation
import com.company.metrix.data.model.User
import com.company.metrix.data.repository.EstimationRepository
import com.company.metrix.data.repository.UserRepository
import com.company.metrix.data.stub.EmployeeFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(val userRepo: UserRepository) :
    ViewModel() { // TODO UserInteractor -> UserRepo ->

    val user: MutableLiveData<User> = MutableLiveData<User>()


    suspend fun getEmployeeInfo(id: Long) {
        //Stub!
        user.value = userRepo.getUserById(id)

    }

}