package com.company.metrix.data.repository

import com.company.metrix.data.model.Diagnostic
import com.company.metrix.data.model.DiagnosticAnswer

interface DiagnosticRepository {
    suspend fun addDiagnostic(item: Diagnostic)
    suspend fun getAllDiagnostic(): List<Diagnostic>
    suspend fun getDiagnosticByTeamId(team_id: Long): Diagnostic
    suspend fun deleteDiagnostic(item: Diagnostic)
    suspend fun updateDiagnostic(item: Diagnostic)
    suspend fun addAnswer(item: DiagnosticAnswer)
    suspend fun getAllAnswers(): List<DiagnosticAnswer>
    suspend fun getAnswersByQuestionId(question_id : Long): DiagnosticAnswer
    suspend fun deleteAnswer(item: DiagnosticAnswer)
    suspend fun updateAnswer(item: DiagnosticAnswer)
}