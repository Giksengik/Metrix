package com.company.metrix.data.model

data class Employee(
    val id: Long,
    val name : String,
    val team_id: Int,
    val position : String,
    val ratings: List<Estimation>,
    val strongSkills: List<String>,
    val weakSkills : List<String>,
    val comments : List<String>,
    val awards: List<String>,
)