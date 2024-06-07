package com.sopt.now.compose.data.di

import com.sopt.now.compose.data.datasource.SharedPrefDataSource
import com.sopt.now.compose.data.datasourceImpl.SharedPrefDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Singleton
    @Binds
    abstract fun bindsSharedPrefDataSource(sharedPrefDataSource: SharedPrefDataSourceImpl): SharedPrefDataSource
}