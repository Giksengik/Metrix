package com.company.metrix.data.model

data class User(
    val id: Long,
    val name : String,
    val email : String,
    var teamId: Long,
    val position : String,
    val role : String,
    val companyName : String
)
