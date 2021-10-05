package com.company.metrix.db

import com.company.metrix.data.model.User
import com.company.metrix.db.entity.UserEntity

interface LocalUserDataProvider {
    suspend fun insertUser(item: User)
    suspend fun getAllUsers(): List<User>
    suspend fun getUserById(id: Long): User
    suspend fun deleteUser(item: User)
    suspend fun getUserByEmail(email : String) : User

}
