package com.company.metrix.db.dao

import androidx.room.*
import com.company.metrix.db.entity.UserEntity
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(item : UserEntity)

    @Query("Select * from `users`" )
    suspend fun getAllUsers() :  List<UserEntity>

    @Query(value = "Select * from `users` where id = :id " )
    suspend fun getUserById(id : Long) : UserEntity

    @Delete()
    suspend fun deleteUser(item : UserEntity)

}