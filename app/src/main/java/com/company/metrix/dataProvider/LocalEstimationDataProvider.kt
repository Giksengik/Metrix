package com.company.metrix.dataProvider

import com.company.metrix.db.entity.EstimationEntity

interface LocalEstimationDataProvider {
    suspend fun insertEstimation(item : EstimationEntity)
    suspend fun getAllEstimations() :  List<EstimationEntity>
    suspend fun deleteEstimation(item : EstimationEntity)
}