package com.company.metrix.dataProvider

import com.company.metrix.db.LocalUserDataProvider
import com.company.metrix.db.dao.EstimationDao
import com.company.metrix.db.dao.UserDao
import com.company.metrix.db.entity.EstimationEntity
import com.company.metrix.db.entity.UserEntity
import javax.inject.Inject

class LocalEstimationDataProviderImpl @Inject constructor(val dao: EstimationDao) : LocalEstimationDataProvider {
    override suspend fun insertEstimation(item: EstimationEntity) = dao.insertEstimation(item)

    override suspend fun getAllEstimations(): List<EstimationEntity>  = dao.getAllEstimations()

    override suspend fun deleteEstimation(item: EstimationEntity) = dao.deleteEstimation(item)

}