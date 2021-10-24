package com.company.metrix.dataProvider

import com.company.metrix.data.model.Team
import com.company.metrix.data.model.User

interface LocalUserDataProvider {
    suspend fun insertUser(item: User)
    suspend fun getAllUsers(): List<User>
    suspend fun getUserById(id: Long): User
    suspend fun deleteUser(item: User)
    suspend fun getUserByEmail(email : String) : User
    suspend fun getUsersByTeam(team_id : Long) :  List<User>
    suspend fun getTeamByTeamAndCompany(team_id : Long, companyName : String) :  Team
    suspend fun getAllTeamsByCompany(companyName : String) :  List<Team>
    suspend fun insertTeam(item : Team)
    suspend fun getAllUsersByTeamAndCompany(team_id : Long, companyName: String) :  List<User>
    suspend fun updateUser(item: User)
    suspend fun getTeamByNameAndCompany(name : String, companyName : String) :  Team
    suspend fun getAllUsersByCompany(companyName: String): List<User>

}
