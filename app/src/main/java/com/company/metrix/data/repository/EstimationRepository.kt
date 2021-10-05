package com.company.metrix.data.repository

import com.company.metrix.data.model.Estimation

interface EstimationRepository {
    suspend fun addEstimation(item: Estimation)
    suspend fun getAllEstimations(): List<Estimation>
    suspend fun deleteEstimation(item: Estimation)
    suspend fun getEstimationsByUserId(user_id: Long): List<Estimation>
    //suspend fun getEstimationsByUserEmail(email: String): List<Estimation>
}
