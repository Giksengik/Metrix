package com.company.metrix.data.stub

import com.company.metrix.data.model.User
import com.company.metrix.data.model.Estimation

class EmployeeFactory{
fun getAllEmployees(): List<User> = listOf(
    User(
        id = 2,
        name = "Егор",
        email = "egor@mail.ru",
        team_id = 3,
        position = "Сотрудник" ,
        role = "Бэкенд"
    ),

    User(
        id = 3,
        name = "Федя",
        email = "fedya@mail.ru",
        team_id = 1,
        position = "Сотрудник" ,
        role = "Android разработчик"
    ),

    User(
        id = 3,
        name = "Иван",
        email = "vanya@mail.ru",
        team_id = 2,
        position = "Сотрудник" ,
        role = "Главный аналитик"
    ),


    User(
        id = 3,
        email = "stepvanya@mail.ru",
        name = "Степан Иванов",
        team_id = 1,
        position = "Сотрудник",
        role = "Менеждер"
    ),

    User(
        id = 3,
        name = "Иван Степанов",
        email = "valnstepya@mail.ru",
        team_id = 1,
        position = "Сотрудник",
        role = "Бэкенд"
    ),
)

}