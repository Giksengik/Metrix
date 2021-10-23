package com.company.metrix.di

import com.company.metrix.data.repository.*
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


    @Singleton
    @Binds
    fun bindPulseRepository(pulseRepositoryImpl: PulseRepositoryImpl): PulseRepository


    @Singleton
    @Binds
    fun bindDiagnosticRepository(diagnosticRepositoryImpl: DiagnosticRepositoryImpl): DiagnosticRepository

}