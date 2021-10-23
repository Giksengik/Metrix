package com.company.metrix.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.company.metrix.db.dao.*
import com.company.metrix.db.entity.*

@Database(
    entities = [EstimationEntity::class, UserEntity::class, TeamEntity::class,
        PulseEntity::class, DiagnosticEntity::class, AnswerDiagnosticEntity::class],
    version = 16,
    exportSchema = false
)
abstract class MetrixDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun estimationDao(): EstimationDao
    abstract fun pulseDao(): PulseDao
    abstract fun diagnosticDao(): DiagnosticDao
    abstract fun diagnosticAnswersDao(): AnswerDiagnosticDao

}