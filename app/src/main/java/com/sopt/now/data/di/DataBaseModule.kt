package com.sopt.now.data.di

import android.content.Context
import androidx.room.Room
import com.sopt.now.data.database.FriendDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Singleton
    @Provides
    fun provideDataBase(
        @ApplicationContext context: Context
    ): FriendDatabase = Room.databaseBuilder(context, FriendDatabase::class.java, "friend.db")
        .createFromAsset("database/friend.db").build()

    @Singleton
    @Provides
    fun provideFriendDao(friendDataBase: FriendDatabase) = friendDataBase.friendDao()
}