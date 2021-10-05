package com.company.metrix.db.dao

import androidx.room.*
import com.company.metrix.db.entity.EstimationEntity

@Dao
interface EstimationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEstimation(item : EstimationEntity)

    @Query("Select * from `estimation`" )
    suspend fun getAllEstimations() :  List<EstimationEntity>

    @Delete()
    suspend fun deleteEstimation(item : EstimationEntity)

}