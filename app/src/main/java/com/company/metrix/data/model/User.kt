package com.company.metrix.data.model

import android.net.Uri

data class User(
    val id: Long,
    val name : String,
    val email : String,
    val team_id: Long,
    val position : String,
    val role : String,
    val companyName : String
)
