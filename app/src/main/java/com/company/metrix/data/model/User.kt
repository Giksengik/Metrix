package com.company.metrix.data.model

import android.net.Uri

data class User(
    val id: Long,
    val name : String,
    val email : String,
    val team_id: Long,
    val position : String,
    val role : String
//    val ratings: List<Estimation>,
//    val strongSkills: List<String>,
//    val weakSkills : List<String>,
//    val awards: List<String>
)
