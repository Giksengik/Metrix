package com.company.metrix.data.repository

import com.company.metrix.data.model.User
import com.company.metrix.db.LocalUserDataProvider
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val localUserProvider: LocalUserDataProvider
) : UserRepository {

    override suspend fun addUser(item: User) {
        localUserProvider.insertUser(item)
    }

    override suspend fun getAllUsers(): List<User> =
        localUserProvider.getAllUsers()

    override suspend fun getUserById(id: Long): User =
        localUserProvider.getUserById(id)

    override suspend fun deleteUser(item: User) = localUserProvider.deleteUser(item)

    override suspend fun getUserByEmail(email: String): User =
        localUserProvider.getUserByEmail(email)

}