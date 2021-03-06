package com.company.metrix.data.model

data class Estimation(
    val id : Long,
    val comment: String,
    val user_id : String,
    val reviewer_id : Long,
    val rate : Double,
    val skillName : String
)