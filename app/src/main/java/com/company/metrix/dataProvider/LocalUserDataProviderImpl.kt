package com.company.metrix.dataProvider

import com.company.metrix.data.model.User
import com.company.metrix.db.LocalUserDataProvider
import com.company.metrix.db.dao.UserDao
import com.company.metrix.db.entity.UserEntity
import javax.inject.Inject

class LocalUserDataProviderImpl  @Inject constructor(val dao: UserDao) : LocalUserDataProvider {
    override suspend fun insertUser(item: User) = dao.insertUser(
        UserEntity(
            id = item.id,
            name = item.name,
            email = item.email,
            team_id = item.team_id,
            position = item.position,
            role = item.role
        )
    )

    override suspend fun getAllUsers(): List<User>  =
        dao.getAllUsers().map {
            User(
                id = it.id,
                name = it.name,
                email = it.email,
                team_id = it.team_id,
                position = it.position,
                role = it.role
            )
        }

    override suspend fun getUserById(id: Long): User {
        val userEntity = dao.getUserById(id)

        return User(
            id = userEntity.id,
            name = userEntity.name,
            email = userEntity.email,
            team_id = userEntity.team_id,
            position = userEntity.position,
            role = userEntity.role
        )
    }


    override suspend fun deleteUser(item: User) = dao.deleteUser(
        UserEntity(
            id = item.id,
            name = item.name,
            email = item.email,
            team_id = item.team_id,
            position = item.position,
            role = item.role
        )
    )

    override suspend fun getUserByEmail(email: String): User {
        val userEntity = dao.getUserByEmail(email)

        return User(
            id = userEntity.id,
            name = userEntity.name,
            email = userEntity.email,
            team_id = userEntity.team_id,
            position = userEntity.position,
            role = userEntity.role
        )
    }
}