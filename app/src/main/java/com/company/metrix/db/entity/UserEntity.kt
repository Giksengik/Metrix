package com.company.metrix.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity (
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val name : String,
    val email : String,
    val companyName : String,
    val team_id: Long,
    val position : String,
    val role : String
)

