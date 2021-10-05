package com.company.metrix.dataProvider

import com.company.metrix.db.LocalUserDataProvider
import com.company.metrix.db.dao.UserDao
import com.company.metrix.db.entity.UserEntity
import javax.inject.Inject

class LocalUserDataProviderImpl  @Inject constructor(val dao: UserDao) : LocalUserDataProvider {
    override suspend fun insertUser(item: UserEntity) = dao.insertUser(item)

    override suspend fun getAllUsers(): List<UserEntity>  = dao.getAllUsers()

    override suspend fun getUserById(id: Long): UserEntity = dao.getUserById(id)

    override suspend fun deleteUser(item: UserEntity) = dao.deleteUser(item)
}