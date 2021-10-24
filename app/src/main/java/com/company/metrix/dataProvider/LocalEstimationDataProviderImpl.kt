package com.company.metrix.dataProvider

import com.company.metrix.data.model.Estimation
import com.company.metrix.db.dao.EstimationDao
import com.company.metrix.db.entity.EstimationEntity
import javax.inject.Inject

class LocalEstimationDataProviderImpl @Inject constructor(val dao: EstimationDao) :
    LocalEstimationDataProvider {
    override suspend fun insertEstimation(item: Estimation) = dao.insertEstimation(
        EstimationEntity(
            estimate_id = item.id,
            user_id = item.user_id,
            reviewer_id = item.reviewer_id,
            rate = item.rate,
            comment = item.comment,
            skillName = item.skillName
        )
    )

    override suspend fun getAllEstimations(): List<Estimation> = dao.getAllEstimations()
        .map {
            Estimation(
                id = it.estimate_id,
                user_id = it.user_id,
                reviewer_id = it.reviewer_id,
                comment = it.comment,
                rate = it.rate,
                skillName = it.skillName
            )
        }

    override suspend fun deleteEstimation(item: Estimation) = dao.deleteEstimation(
        EstimationEntity(
            estimate_id = item.id,
            user_id = item.user_id,
            reviewer_id = item.reviewer_id,
            rate = item.rate,
            comment = item.comment,
            skillName = item.skillName
        )
    )

    override suspend fun getEstimationsByUserId(user_id: Long): List<Estimation> =
        dao.getEstimationsByUserId(user_id).map {
            Estimation(
                id = it.estimate_id,
                user_id = it.user_id,
                reviewer_id = it.reviewer_id,
                comment = it.comment,
                rate = it.rate,
                skillName = it.skillName
            )
        }

}