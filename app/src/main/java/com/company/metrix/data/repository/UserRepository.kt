package com.company.metrix.data.repository

import com.company.metrix.data.model.User

interface UserRepository {
    suspend fun addUser(item : User)
    suspend fun getAllUsers() :  List<User>
    suspend fun getUserById(id : Long) : User
    suspend fun deleteUser(item : User)
    suspend fun getUserByEmail(email : String) : User
    suspend fun getUsersByTeam(team_id : Long) : List<User>

}