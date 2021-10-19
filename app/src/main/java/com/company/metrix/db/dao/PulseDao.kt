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

    @Delete()
    suspend fun deletePulse(item: PulseEntity)

    @Update
    suspend fun updatePulse(item: PulseEntity)
}