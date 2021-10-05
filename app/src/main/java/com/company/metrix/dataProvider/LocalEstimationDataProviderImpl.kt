package com.company.metrix.dataProvider

import com.company.metrix.data.model.Estimation
import com.company.metrix.db.LocalUserDataProvider
import com.company.metrix.db.dao.EstimationDao
import com.company.metrix.db.dao.UserDao
import com.company.metrix.db.entity.EstimationEntity
import com.company.metrix.db.entity.UserEntity
import javax.inject.Inject

class LocalEstimationDataProviderImpl @Inject constructor(val dao: EstimationDao) :
    LocalEstimationDataProvider {
    override suspend fun insertEstimation(item: Estimation) = dao.insertEstimation(
        EstimationEntity(
            estimation_id = 1,
            user_id = item.user_id,
            reviewer_id = item.reviewer_id,
            polite = item.polite,
            mobile = item.mobile,
            professional = item.professional,
            speed = item.speed,
            friendly = item.friendly,
            comment = item.comment

        )
    )

    override suspend fun getAllEstimations(): List<Estimation> = dao.getAllEstimations()
        .map {
            Estimation(
                user_id = it.user_id,
                reviewer_id = it.reviewer_id,
                polite = it.polite,
                mobile = it.mobile,
                professional = it.professional,
                speed = it.speed,
                friendly = it.friendly,
                comment = it.comment

            )
        }

    override suspend fun deleteEstimation(item: Estimation) = dao.deleteEstimation(
        EstimationEntity(
            estimation_id = 1,
            user_id = item.user_id,
            reviewer_id = item.reviewer_id,
            polite = item.polite,
            mobile = item.mobile,
            professional = item.professional,
            speed = item.speed,
            friendly = item.friendly,
            comment = item.comment

        )
    )

    override suspend fun getEstimationsByUserId(user_id: Long): List<Estimation> =
        dao.getEstimationsByUserId(user_id).map {
            Estimation(
                user_id = it.user_id,
                reviewer_id = it.reviewer_id,
                polite = it.polite,
                mobile = it.mobile,
                professional = it.professional,
                speed = it.speed,
                friendly = it.friendly,
                comment = it.comment

            )
        }

}