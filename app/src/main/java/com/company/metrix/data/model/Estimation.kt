package com.company.metrix.data.model

data class Estimation(
    val comment: String,
    val user_id : Long,
    val reviewer_id : Long,
    val rate : Double,
    val skillName : String
)