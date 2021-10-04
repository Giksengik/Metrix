package com.company.metrix.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.metrix.data.model.Employee
import com.company.metrix.data.model.Estimation
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() { // TODO UserInteractor -> UserRepo ->

    val employee: MutableLiveData<Employee> = MutableLiveData<Employee>()

    fun getEmployeeInfo() {
        //Stub!
        employee.value = Employee(
            id = 1,
            name = "Yana",
            team_id = 1,
            position = "Руководитель",
            ratings = listOf(Estimation(2, 3.0, 3.0, 3.0, 3.0, 3.0),
                Estimation(3, 3.5, 4.0, 3.5, 3.0, 5.0),
                Estimation(4, 5.0, 4.5, 3.0, 3.0, 5.0)
                ),
            strongSkills = listOf("опыт ведения переговоров","agile", "опыт составления отчетов"),
            weakSkills = listOf("RxJava", "Kotlin", "Android Jetpack"),
            comments = listOf("ужасный сотрудник!", "лучший сотрудник!"),
            awards = listOf("наиболее продвинутый")
        )
    }

}