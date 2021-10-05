package com.company.metrix.db

import com.company.metrix.db.entity.UserEntity

interface LocalUserDataProvider {
    suspend fun insertUser(item : UserEntity)
    suspend fun getAllUsers() :  List<UserEntity>
    suspend fun getUserById(id : Long) : UserEntity
    suspend fun deleteUser(item : UserEntity)
}
