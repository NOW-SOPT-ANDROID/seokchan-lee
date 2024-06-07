package com.sopt.now.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sopt.now.data.model.local.FriendDao
import com.sopt.now.data.model.local.FriendEntity

@Database(entities = [FriendEntity::class], version = 1)
abstract class FriendDatabase : RoomDatabase() {
    abstract fun friendDao(): FriendDao
}