package com.company.metrix.di

import com.company.metrix.dataProvider.LocalEstimationDataProvider
import com.company.metrix.dataProvider.LocalEstimationDataProviderImpl
import com.company.metrix.dataProvider.LocalUserDataProviderImpl
import com.company.metrix.db.LocalUserDataProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface LocalDataModule {

    @Binds
    @Singleton
    fun bindUserDataProvider(userProvider: LocalUserDataProviderImpl)
            : LocalUserDataProvider

    @Binds
    @Singleton
    fun bindEstimationDataProvider(estimationProvider: LocalEstimationDataProviderImpl)
            : LocalEstimationDataProvider


}