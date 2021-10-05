package com.company.metrix.data.model

data class Statistic(
    val user_id : Long,
    val average : Double,
    val allComments : MutableList<String>
)