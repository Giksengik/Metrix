package com.company.metrix.di

import android.content.Context
import androidx.room.Room
import com.company.metrix.db.MetrixDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    @Singleton
    fun provideMetrixDatabase(@ApplicationContext context : Context) : MetrixDatabase =
        Room.databaseBuilder(context, MetrixDatabase::class.java, "metrix-db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideUserDao(database: MetrixDatabase) = database.userDao()

    @Provides
    @Singleton
    fun provideEstimationDao(database: MetrixDatabase) = database.estimationDao()

    @Provides
    @Singleton
    fun provideDiagnosticDao(database: MetrixDatabase) = database.diagnosticDao()

    @Provides
    @Singleton
    fun provideAnswerDiagnosticDao(database: MetrixDatabase) = database.diagnosticAnswersDao()
}