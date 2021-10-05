package com.company.metrix.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.metrix.data.model.User
import com.company.metrix.data.model.Estimation
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() { // TODO UserInteractor -> UserRepo ->

    val user: MutableLiveData<User> = MutableLiveData<User>()

    fun getStatisticByUserId(){

    }

    fun getEmployeeInfo() {
        //Stub!
        user.value = User(
            id = 1,
            name = "Yana",
            team_id = 1,
            position = "Руководитель"
//            ratings = listOf(Estimation(2, 3.0, 3.0, 3.0, 3.0, 3.0,"Увольте!"),
//                Estimation(3, 3.5, 4.0, 3.5, 3.0, 5.0, "Сойдет"),
//                Estimation(4, 5.0, 4.5, 3.0, 3.0, 5.0, "Не успевает в дедлайны")
//                ),
//            strongSkills = listOf("опыт ведения переговоров","agile", "опыт составления отчетов"),
//            weakSkills = listOf("RxJava", "Kotlin", "Android Jetpack"),
//           // comments = listOf("ужасный сотрудник!", "лучший сотрудник!"),
//            awards = listOf("наиболее продвинутый")
        )
    }

}