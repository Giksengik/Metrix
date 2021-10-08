package com.company.metrix.data.stub

import com.company.metrix.data.model.User
import com.company.metrix.data.model.Estimation
import com.company.metrix.data.model.Team

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
        role = "Старший разработчик",
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
        team_id = 2,
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

fun getAllTeams( ) : List<Team> = listOf(
    Team(
        1,
        "Tinkoff",
        1,
        "Android"
    ),
    Team(
        2,
        "Tinkoff",
        2,
        "Персонал"
    ),
    Team(
        3,
        "Tinkoff",
        3,
        "Бэкенд"
    ),

    Team(
        4,
        "Tinkoff",
        4,
        "Аналитика"
    ),

    Team(
        5,
        "Google",
        1,
        "Бэкендеры"
    ),

    Team(
        6,
        "Google",
        2,
        "Аналитика"
    ),
)
}