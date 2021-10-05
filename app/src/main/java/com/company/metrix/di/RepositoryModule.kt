package com.company.metrix.di

import com.company.metrix.data.repository.EstimationRepository
import com.company.metrix.data.repository.EstimationRepositoryImpl
import com.company.metrix.data.repository.UserRepository
import com.company.metrix.data.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Singleton
    @Binds
    fun bindEstimationRepository(estimationRepositoryImpl: EstimationRepositoryImpl): EstimationRepository


}