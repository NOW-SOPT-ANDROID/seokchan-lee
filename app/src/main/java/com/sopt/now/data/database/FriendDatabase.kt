package com.sopt.now.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sopt.now.data.model.FriendDao
import com.sopt.now.data.model.FriendEntity

@Database(entities = [FriendEntity::class], version = 2)
abstract class FriendDatabase : RoomDatabase() {
    abstract fun friendDao(): FriendDao
}