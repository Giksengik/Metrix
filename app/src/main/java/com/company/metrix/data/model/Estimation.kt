package com.company.metrix.data.model

data class Estimation(
    val user_id : Long,
    val reviewer_id : Long,
    val polite : Double,
    val mobile : Double,
    val professional : Double,
    val speed : Double,
    val friendly : Double,
    val comment: String
)