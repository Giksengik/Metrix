package com.company.metrix.data.model

data class DiagnosticAnswer(
    val value: String,
    val team_id: Long,
    val question_id: Int,
    val chosen_check_box_number: Int
)