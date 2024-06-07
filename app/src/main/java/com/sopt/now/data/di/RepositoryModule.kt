package com.sopt.now.data.di

import com.sopt.now.data.repository.FriendRepository
import com.sopt.now.data.repository.FriendRepositoryImpl
import com.sopt.now.data.repository.SharedPrefRepository
import com.sopt.now.data.repository.SharedPrefRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindsFriendRepository(
        friendRepository: FriendRepositoryImpl
    ): FriendRepository

    @Singleton
    @Binds
    abstract fun bindsSharedPrefRepository(
        sharedPrefRepository: SharedPrefRepositoryImpl
    ): SharedPrefRepository
}