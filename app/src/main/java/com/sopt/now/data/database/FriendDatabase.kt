package com.sopt.now.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sopt.now.data.model.FriendDao
import com.sopt.now.data.model.FriendEntity

@Database(entities = [FriendEntity::class], version = 2)
abstract class FriendDatabase : RoomDatabase() {
    abstract fun friendDao(): FriendDao

    companion object{
        @Volatile
        private var INSTANCE: FriendDatabase? = null

        fun getDatabase(context: Context): FriendDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FriendDatabase::class.java,
                    "friend_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}