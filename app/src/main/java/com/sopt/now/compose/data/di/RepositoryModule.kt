package com.sopt.now.compose.data.di

import com.sopt.now.compose.data.repository.SharedPrefRepository
import com.sopt.now.compose.data.repository.SharedPrefRepositoryImpl
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
    abstract fun bindsSharedPrefRepository(
        sharedPrefRepository: SharedPrefRepositoryImpl
    ): SharedPrefRepository
}