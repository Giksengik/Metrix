package com.company.metrix.data.repository

import com.company.metrix.data.model.Diagnostic
import com.company.metrix.data.model.DiagnosticAnswer
import com.company.metrix.dataProvider.LocalDiagnosticDataProvider
import javax.inject.Inject

class DiagnosticRepositoryImpl @Inject constructor(
    private val localDiagnosticProvider: LocalDiagnosticDataProvider
) : DiagnosticRepository {

    override suspend fun addDiagnostic(item: Diagnostic) = localDiagnosticProvider.insertDiagnostic(item)

    override suspend fun getAllDiagnostic(): List<Diagnostic> = localDiagnosticProvider.getAllDiagnostic()

    override suspend fun getDiagnosticByTeamId(team_id: Long): Diagnostic =
        localDiagnosticProvider.getDiagnosticByTeamId(team_id)

    override suspend fun deleteDiagnostic(item: Diagnostic) = localDiagnosticProvider.deleteDiagnostic(item)

    override suspend fun updateDiagnostic(item: Diagnostic) = localDiagnosticProvider.updateDiagnostic(item)

    override suspend fun addAnswer(item: DiagnosticAnswer) = localDiagnosticProvider.insertAnswer(item)

    override suspend fun getAllAnswers(): List<DiagnosticAnswer> = localDiagnosticProvider.getAllAnswers()

    override suspend fun getAnswersByQuestionId(question_id: Long): DiagnosticAnswer =
        localDiagnosticProvider.getAnswersByQuestionId(question_id)

    override suspend fun deleteAnswer(item: DiagnosticAnswer) = localDiagnosticProvider.deleteAnswer(item)

    override suspend fun updateAnswer(item: DiagnosticAnswer) = localDiagnosticProvider.updateAnswer(item)

}