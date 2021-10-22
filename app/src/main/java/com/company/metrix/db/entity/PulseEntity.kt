package com.company.metrix.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pulse")
data class PulseEntity(
    val question_id : Long,
    val team_id : Long,
    @PrimaryKey(autoGenerate = false)
    val companyName : String,
    val votesOne : Int,
    val votesTwo : Int,
    val votesThree : Int,
    val votesFour : Int
)