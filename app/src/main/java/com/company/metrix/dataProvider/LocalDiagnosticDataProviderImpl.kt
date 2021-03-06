package com.company.metrix.dataProvider

import com.company.metrix.data.model.Diagnostic
import com.company.metrix.data.model.DiagnosticAnswer
import com.company.metrix.db.dao.AnswerDiagnosticDao
import com.company.metrix.db.dao.DiagnosticDao
import com.company.metrix.db.entity.AnswerDiagnosticEntity
import com.company.metrix.db.entity.DiagnosticEntity
import javax.inject.Inject

class LocalDiagnosticDataProviderImpl @Inject constructor(
    private val daoDiagnostic: DiagnosticDao,
    private val daoDiagnosticAnswer: AnswerDiagnosticDao

) : LocalDiagnosticDataProvider {
    override suspend fun insertDiagnostic(item: Diagnostic) =
        daoDiagnostic.insertDiagnostic(
            DiagnosticEntity(
                team_id = item.team_id,
                question_id = item.question_id,
                value = item.value
            )
        )

    override suspend fun getAllDiagnostic(): List<Diagnostic> =
        daoDiagnostic.getAllDiagnostic().map {
            Diagnostic(
                team_id = it.team_id,
                question_id = it.question_id,
                value = it.value
            )
        }

    override suspend fun getDiagnosticByTeamId(team_id: Long): Diagnostic {
        val entity = daoDiagnostic.getDiagnosticByTeamId(team_id)

        return Diagnostic(
            team_id = entity.team_id,
            question_id = entity.question_id,
            value = entity.value
        )
    }


    override suspend fun deleteDiagnostic(item: Diagnostic) =
        daoDiagnostic.deleteDiagnostic(
            DiagnosticEntity(
                team_id = item.team_id,
                question_id = item.question_id,
                value = item.value
            )
        )


    override suspend fun updateDiagnostic(item: Diagnostic) =
        daoDiagnostic.updateDiagnostic(
            DiagnosticEntity(
                team_id = item.team_id,
                question_id = item.question_id,
                value = item.value
            )
        )

    override suspend fun insertAnswer(item: DiagnosticAnswer) =
        daoDiagnosticAnswer.insertAnswer(
            AnswerDiagnosticEntity(
                value = item.value,
                team_id = item.team_id,
                question_id = item.question_id,
                chosen_check_box_number = item.chosen_check_box_number
            )
        )

    override suspend fun getAllAnswers(): List<DiagnosticAnswer> =
        daoDiagnosticAnswer.getAllAnswers().map {
            DiagnosticAnswer(
                value = it.value,
                team_id = it.team_id,
                question_id = it.question_id,
                chosen_check_box_number = it.chosen_check_box_number
            )
        }

    override suspend fun getAnswersByQuestionId(question_id: Long): DiagnosticAnswer {
        val entity = daoDiagnosticAnswer.getAnswersByQuestionId(question_id)

        return DiagnosticAnswer(
            value = entity.value,
            team_id = entity.team_id,
            question_id = entity.question_id,
            chosen_check_box_number = entity.chosen_check_box_number
        )
    }

    override suspend fun deleteAnswer(item: DiagnosticAnswer) =
        daoDiagnosticAnswer.updateAnswer(
            AnswerDiagnosticEntity(
                value = item.value,
                team_id = item.team_id,
                question_id = item.question_id,
                chosen_check_box_number = item.chosen_check_box_number
            )
        )

    override suspend fun updateAnswer(item: DiagnosticAnswer) =
        daoDiagnosticAnswer.updateAnswer(
            AnswerDiagnosticEntity(
                value = item.value,
                team_id = item.team_id,
                question_id = item.question_id,
                chosen_check_box_number = item.chosen_check_box_number
            )
        )

}