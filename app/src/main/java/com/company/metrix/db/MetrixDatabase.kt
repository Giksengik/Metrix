package com.company.metrix.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.company.metrix.db.dao.EstimationDao
import com.company.metrix.db.dao.UserDao
import com.company.metrix.db.entity.EstimationEntity
import com.company.metrix.db.entity.StatsEntity
import com.company.metrix.db.entity.UserEntity

@Database(entities = [EstimationEntity::class, UserEntity::class] , version = 1,
    exportSchema = false)
abstract class MetrixDatabase : RoomDatabase(){
    abstract fun userDao() : UserDao
    abstract fun estimationDao() : EstimationDao
}