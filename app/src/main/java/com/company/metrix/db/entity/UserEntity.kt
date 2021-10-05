package com.company.metrix.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.company.metrix.data.model.Estimation

@Entity(tableName = "users")
data class UserEntity (
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val name : String,
    val team_id: Int,
    val position : String
//    @Embedded
//    val ratings: Array<Estimation>,
//    @Embedded
//    val strongSkills: Array<String>,
//    @Embedded
//    val weakSkills : Array<String>
)

