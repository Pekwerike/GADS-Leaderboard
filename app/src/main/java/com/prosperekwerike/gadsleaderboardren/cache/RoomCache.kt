package com.prosperekwerike.gadsleaderboardren.cache

import android.content.Context
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [LearningLeadersEntity::class, SkillsIQLeadersEntity::class],
    version = 1,
    exportSchema = true
)
abstract class RoomCache : RoomDatabase() {
    abstract fun cacheDao() : CacheDao

}

private lateinit var INSTANCE : RoomCache

fun getLocalCache(context: Context) : RoomCache{
    synchronized(RoomCache::class.java){
        if(!::INSTANCE.isInitialized){
            INSTANCE = Room.databaseBuilder(
                context,
            RoomCache::class.java,
            "local_database_cache")
                .build()
        }
    }
    return INSTANCE
}