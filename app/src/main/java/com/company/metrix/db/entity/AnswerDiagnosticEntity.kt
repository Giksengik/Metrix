package com.company.metrix.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "diagnostic_answer")
data class AnswerDiagnosticEntity (
    @PrimaryKey(autoGenerate = true)
    val id : Long,
    val question_id : Long,
    val chosen_check_box_number: Int
)