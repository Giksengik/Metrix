package com.company.metrix.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "diagnostic")
data class DiagnosticEntity(
    val team_id: Long,
    val question_id: Long,
    @PrimaryKey(autoGenerate = false)
    val value: String
)