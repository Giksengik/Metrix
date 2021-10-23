package com.company.metrix.data.model

import androidx.room.PrimaryKey

data class DiagnosticAnswer(
    val id: Long,
    val question_id: Long,
    val chosen_check_box_number: Int
)