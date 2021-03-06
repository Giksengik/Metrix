package com.company.metrix.data.repository

import com.company.metrix.data.model.Team
import com.company.metrix.data.model.User
import com.company.metrix.dataProvider.LocalUserDataProvider
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val localUserProvider: LocalUserDataProvider
) : UserRepository {

    override suspend fun addUser(item: User) =
        localUserProvider.insertUser(item)

    override suspend fun getAllUsers(): List<User> =
        localUserProvider.getAllUsers()

    override suspend fun getUserById(id: Long): User =
        localUserProvider.getUserById(id)

    override suspend fun deleteUser(item: User) = localUserProvider.deleteUser(item)

    override suspend fun getUserByEmail(email: String): User =
        localUserProvider.getUserByEmail(email)

    override suspend fun getUsersByTeam(team_id: Long): List<User> =
        localUserProvider.getUsersByTeam(team_id)

    override suspend fun getTeamByTeamAndCompany(team_id: Long, companyName: String): Team =
        localUserProvider.getTeamByTeamAndCompany(team_id, companyName)

    override suspend fun getAllTeamsByCompany(companyName: String): List<Team> =
        localUserProvider.getAllTeamsByCompany(companyName)

    override suspend fun addTeam(item: Team) = localUserProvider.insertTeam(item)
    override suspend fun getAllUsersByTeamAndCompany(
        team_id: Long,
        companyName: String
    ): List<User> =
        localUserProvider.getAllUsersByTeamAndCompany(team_id, companyName)

    override suspend fun updateUser(item: User) =
        localUserProvider.updateUser(item)

    override suspend fun getTeamByNameAndCompany(name: String, companyName: String): Team =
        localUserProvider.getTeamByNameAndCompany(name, companyName)

    override suspend fun getAllUsersByCompany(companyName: String): List<User> =
        localUserProvider.getAllUsersByCompany(companyName)

}