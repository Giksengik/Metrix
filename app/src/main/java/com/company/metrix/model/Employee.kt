package com.company.metrix.model

data class Employee(
    val id: Int,
    val team: Int,
    val ratings: List<Double>,
    val strongSkills: List<String>,
    val weakSkills : List<String>,
    val achievements: List<String>
)
