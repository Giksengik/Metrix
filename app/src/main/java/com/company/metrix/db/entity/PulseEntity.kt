package com.company.metrix.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pulse")
data class PulseEntity(
    @PrimaryKey(autoGenerate = false)
    val id : Long,
    val question_id: Long,
    val team_id: Long,
    val companyName: String,
    val votesOne: Int,
    val votesTwo: Int,
    val votesThree: Int,
    val votesFour: Int
)