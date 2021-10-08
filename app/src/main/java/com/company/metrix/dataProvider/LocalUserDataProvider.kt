package com.company.metrix.db

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.company.metrix.data.model.Team
import com.company.metrix.data.model.User
import com.company.metrix.db.entity.TeamEntity
import com.company.metrix.db.entity.UserEntity

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
}
