package com.company.metrix.data.model

import androidx.room.PrimaryKey

data class DiagnosticEntity(
    val id: Long,
    val question_id: Long,
    val value: String
)