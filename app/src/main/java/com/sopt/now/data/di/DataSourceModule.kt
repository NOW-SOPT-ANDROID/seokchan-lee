package com.sopt.now.data.di

import com.sopt.now.data.datasource.FriendDataSource
import com.sopt.now.data.datasource.SharedPrefDataSource
import com.sopt.now.data.datasourceImpl.FriendDataSourceImpl
import com.sopt.now.data.datasourceImpl.SharedPrefDataSourceImpl
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
    abstract fun bindsFriendDataSource(friendDataSource: FriendDataSourceImpl): FriendDataSource

    @Singleton
    @Binds
    abstract fun bindsSharedPRefDataSource(sharedPrefDataSource: SharedPrefDataSourceImpl): SharedPrefDataSource
}