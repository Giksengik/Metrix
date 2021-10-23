package com.company.metrix.db.dao

import androidx.room.*
import com.company.metrix.db.entity.DiagnosticEntity

@Dao
interface DiagnosticDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDiagnostic(item: DiagnosticEntity)

    @Query("Select * from `diagnostic`")
    suspend fun getAllDiagnostic(): List<DiagnosticEntity>

    @Query("Select * from `diagnostic` where team_id = :team_id ")
    suspend fun getDiagnosticByTeamId(team_id: Long): DiagnosticEntity

    @Delete
    suspend fun deleteDiagnostic(item: DiagnosticEntity)

    @Update
    suspend fun updateDiagnosti(item: DiagnosticEntity)
}