package com.company.metrix.db.dao

import androidx.room.*
import com.company.metrix.db.entity.PulseEntity

@Dao
interface PulseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPulse(item: PulseEntity)

    @Query("Select * from `pulse`")
    suspend fun getAllPulse(): List<PulseEntity>

    @Query("Select * from `pulse` where companyName = :companyName ")
    suspend fun getPulseByCompany(companyName : String): PulseEntity

    @Query("Select * from `pulse` where companyName = :companyName and question_id = :question_id and team_id = :team_id")
    suspend fun getPulseByCompanyAndIdQuestion(companyName : String, question_id : Long, team_id : Long): PulseEntity

    @Delete
    suspend fun deletePulse(item: PulseEntity)

    @Update
    suspend fun updatePulse(item: PulseEntity)
}