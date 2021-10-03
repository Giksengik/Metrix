package com.company.metrix.data.model

data class Employee(
    val id: String,
    val team: Int,
    val ratings: List<Double>,
    val strongSkills: List<String>,
    val weakSkills : List<String>,
    val comments : List<String>,
    val awards: List<String>
)
