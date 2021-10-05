package com.company.metrix.dataProvider

import com.company.metrix.data.model.Estimation
import com.company.metrix.db.entity.EstimationEntity

interface LocalEstimationDataProvider {
    suspend fun insertEstimation(item: Estimation)
    suspend fun getAllEstimations(): List<Estimation>
    suspend fun deleteEstimation(item: Estimation)
    suspend fun getEstimationsByUserId(user_id: Long): List<Estimation>

}