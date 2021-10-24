package com.company.metrix.db.dao

import androidx.room.*
import com.company.metrix.db.entity.EstimationEntity

@Dao
interface EstimationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEstimation(item : EstimationEntity)

    @Query("Select * from `estimation`" )
    suspend fun getAllEstimations() :  List<EstimationEntity>

    @Query("Select * from `estimation` where user_id = :user_id " )
    suspend fun getEstimationsByUserId(user_id : Long) :  List<EstimationEntity>

    @Delete
    suspend fun deleteEstimation(item : EstimationEntity)

}