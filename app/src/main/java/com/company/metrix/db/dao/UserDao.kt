package com.company.metrix.db.dao

import androidx.room.*
import com.company.metrix.db.entity.TeamEntity
import com.company.metrix.db.entity.UserEntity
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(item : UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeam(item : TeamEntity)

    @Query("Select * from `users`" )
    suspend fun getAllUsers() :  List<UserEntity>

    @Query(value = "Select * from `users` where id = :id " )
    suspend fun getUserById(id : Long) : UserEntity

    @Query(value = "Select * from `users` where email = :email " )
    suspend fun getUserByEmail(email : String) : UserEntity

    @Query(value = "Select * from `users` where team_id = :team_id " )
    suspend fun getAllUsersByTeam(team_id : Long) :  List<UserEntity>

    @Query(value = "Select * from `users` where team_id = :team_id and companyName = :companyName" )
    suspend fun getAllUsersByTeamAndCompany(team_id : Long, companyName: String) :  List<UserEntity>

    @Query(value = "Select * from `teams` where team_id = :team_id and companyName = :companyName" )
    suspend fun getTeamByTeamAndCompany(team_id : Long, companyName : String) :  TeamEntity

    @Query(value = "Select * from `teams` where companyName = :companyName " )
    suspend fun getAllTeamsByCompany(companyName : String) :  List<TeamEntity>


    @Delete()
    suspend fun deleteUser(item : UserEntity)

}