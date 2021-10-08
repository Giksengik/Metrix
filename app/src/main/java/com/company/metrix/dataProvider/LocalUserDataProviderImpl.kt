package com.company.metrix.dataProvider

import com.company.metrix.data.model.Team
import com.company.metrix.data.model.User
import com.company.metrix.db.LocalUserDataProvider
import com.company.metrix.db.dao.UserDao
import com.company.metrix.db.entity.TeamEntity
import com.company.metrix.db.entity.UserEntity
import javax.inject.Inject

class LocalUserDataProviderImpl @Inject constructor(val dao: UserDao) : LocalUserDataProvider {

    override suspend fun insertUser(item: User) = dao.insertUser(
        UserEntity(
            id = item.id,
            name = item.name,
            email = item.email,
            team_id = item.team_id,
            position = item.position,
            role = item.role,
            companyName = item.companyName
        )
    )

    override suspend fun getAllUsers(): List<User> =
        dao.getAllUsers().map {
            User(
                id = it.id,
                name = it.name,
                email = it.email,
                team_id = it.team_id,
                position = it.position,
                role = it.role,
                companyName = it.companyName
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
            role = userEntity.role,
            companyName = userEntity.companyName
        )
    }


    override suspend fun deleteUser(item: User) = dao.deleteUser(
        UserEntity(
            id = item.id,
            name = item.name,
            email = item.email,
            team_id = item.team_id,
            position = item.position,
            role = item.role,
            companyName = item.companyName
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
            role = userEntity.role,
            companyName = userEntity.companyName
        )
    }

    override suspend fun getUsersByTeam(team_id: Long): List<User> =
        dao.getAllUsersByTeam(team_id)
            .map {
                User(
                    id = it.id,
                    name = it.name,
                    email = it.email,
                    team_id = it.team_id,
                    position = it.position,
                    role = it.role,
                    companyName = it.companyName
                )
            }

    override suspend fun getTeamByTeamAndCompany(team_id: Long, companyName: String): Team {
        val team = dao.getTeamByTeamAndCompany(team_id, companyName)

        return Team(
            team.id,
            team.companyName,
            team.team_id,
            team.team_name
        )
    }

    override suspend fun getAllTeamsByCompany(companyName: String): List<Team> =
        dao.getAllTeamsByCompany(companyName)
            .map {
                Team(
                    it.id,
                    it.companyName,
                    it.team_id,
                    it.team_name
                )
            }

    override suspend fun insertTeam(item: Team) =
        dao.insertTeam(TeamEntity(
            item.id,
            item.companyName,
            item.team_id,
            item.team_name
        ))

}