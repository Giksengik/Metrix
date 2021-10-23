package com.company.metrix.db.dao

import androidx.room.*
import com.company.metrix.db.entity.AnswerDiagnosticEntity

@Dao
interface AnswerDiagnosticDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnswer(item: AnswerDiagnosticEntity)

    @Query("Select * from `diagnostic_answer`")
    suspend fun getAllAnswers(): List<AnswerDiagnosticEntity>

    @Query("Select * from `diagnostic_answer` where question_id = :question_id ")
    suspend fun getAnswersByQuestionId(question_id : Long): AnswerDiagnosticEntity

    @Delete
    suspend fun deleteAnswer(item: AnswerDiagnosticEntity)

    @Update
    suspend fun updateAnswer(item: AnswerDiagnosticEntity)
}