package com.company.metrix.data.stub

import com.company.metrix.data.model.User
import com.company.metrix.data.model.Estimation

class EmployeeFactory{
fun getAllEmployees(): List<User> = listOf(
    User(
        id = 2,
        name = "Егор",
        team_id = 3,
        position = "Сотрудник"//,
//        ratings = listOf(Estimation(2, 3.0, 3.0, 3.0, 3.0, 3.0,"Плохо"),
//            Estimation(3, 3.5, 4.0, 3.5, 3.0, 5.0,"Такое себе"),
//            Estimation(4, 5.0, 4.5, 3.0, 3.0, 5.0,"Норм")
//        ),
//        strongSkills = listOf("опыт ведения переговоров","agile", "опыт составления отчетов"),
//        weakSkills = listOf("RxJava", "Kotlin", "Stackoverflow", "Googling"),
//      //  comments = listOf("лучше всех рисует кнопки!" ),
//        awards = listOf("лучший в команде")
    ),

    User(
        id = 3,
        name = "Федя",
        team_id = 4,
        position = "Сотрудник",
//        ratings = listOf(Estimation(2, 3.0, 3.0, 3.0, 3.0, 3.0, "Ужас слов нет"),
//            Estimation(3, 3.5, 4.0, 3.5, 3.0, 5.0, "На троечку"),
//            Estimation(4, 5.0, 4.5, 3.0, 3.0, 5.0, "Крутой спец!")
//        ),
//        strongSkills = listOf("опыт ведения переговоров","agile", "опыт составления отчетов"),
//        weakSkills = listOf("Coroutines", "Java", "FireBase"),
//      //  comments = listOf("молодец!", "крутой!"),
//        awards = listOf("самый быстрый")
    ),

    User(
        id = 3,
        name = "Иван",
        team_id = 2,
        position = "Сотрудник",
//        ratings = listOf(Estimation(2, 3.0, 3.0, 3.0, 3.0, 3.0 , "Отстой"),
//            Estimation(3, 3.5, 4.0, 3.5, 3.0, 5.0,"Мне понравился"),
//            Estimation(4, 5.0, 4.5, 3.0, 3.0, 5.0, "Все ок, но медленно ")
//        ),
//        strongSkills = listOf("опыт ведения переговоров","agile", "опыт составления отчетов"),
//        weakSkills = listOf("Java", "Spring", "Multithreading", "C++"),
//     //   comments = listOf("ужасный сотрудник!", "лучший сотрудник!"),
//        awards = listOf("сама вежливость")
    ),
)

}