package com.company.metrix.data.repository

import com.company.metrix.data.model.Estimation
import com.company.metrix.dataProvider.LocalEstimationDataProvider
import javax.inject.Inject

class EstimationRepositoryImpl @Inject constructor(
    private val localEstimationProvider: LocalEstimationDataProvider
) : EstimationRepository {

    override suspend fun addEstimation(item: Estimation) {
        localEstimationProvider.insertEstimation(item)
    }
    override suspend fun getAllEstimations(): List<Estimation> =
        localEstimationProvider.getAllEstimations()

    override suspend fun deleteEstimation(item: Estimation) =
        localEstimationProvider.deleteEstimation(item)

    override suspend fun getEstimationsByUserId(user_id: Long): List<Estimation> =
        localEstimationProvider.getEstimationsByUserId(user_id)

}