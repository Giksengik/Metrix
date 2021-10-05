package com.company.metrix.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.metrix.data.model.Estimation
import com.company.metrix.data.model.User
import com.company.metrix.data.repository.EstimationRepository
import com.company.metrix.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(val userRepo: UserRepository ) :
    ViewModel() { // TODO UserInteractor -> UserRepo ->

    val user: MutableLiveData<User> = MutableLiveData<User>()

    suspend fun initial() {
        val user1 = User(
            id = 1,
            name = "Yana",
            email = "monsterglad12@gmail.com",
            team_id = 1,
            position = "Руководитель",
            role = "Тим лид гений босс"
        )
        //  repository.deleteUser(user1)
        userRepo.addUser(user1)

    }

    suspend fun getEmployeeInfo(id: Long) {
        //Stub!
        user.value = userRepo.getUserById(id)

    }

}