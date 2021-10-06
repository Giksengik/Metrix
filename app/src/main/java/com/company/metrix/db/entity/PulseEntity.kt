package com.company.metrix.db.entity

import androidx.room.Entity

@Entity(tableName = "pulse")
data class PulseEntity(
    val team_id : Long
)