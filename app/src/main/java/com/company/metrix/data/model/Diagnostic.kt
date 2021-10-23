package com.company.metrix.data.model

data class Diagnostic (
    val id: Long,
    val team_id : Long,
    val question_id: Long,
    val value: String
)