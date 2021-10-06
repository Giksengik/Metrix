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
        role = "Бэкенд",
        companyName = "Tinkoff"
    ),

    User(
        id = 4,
        name = "Федя",
        email = "fedya@mail.ru",
        team_id = 1,
        position = "Сотрудник" ,
        role = "Android разработчик",
        companyName = "Tinkoff"
    ),

    User(
        id = 5,
        name = "Иван",
        email = "vanya@mail.ru",
        team_id = 2,
        position = "Сотрудник" ,
        role = "Главный аналитик",
        companyName = "Google"
    ),


    User(
        id = 6,
        email = "stepvanya@mail.ru",
        name = "Степан Иванов",
        team_id = 1,
        position = "Сотрудник",
        role = "Менеждер",
        companyName = "Tinkoff"
    ),

    User(
        id = 7,
        name = "Иван Степанов",
        email = "valnstepya@mail.ru",
        team_id = 1,
        position = "Сотрудник",
        role = "Бэкенд",
        companyName = "Google"
    ),
)

}