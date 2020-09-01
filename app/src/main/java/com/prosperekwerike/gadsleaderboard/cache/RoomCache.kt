package com.prosperekwerike.gadsleaderboard.cache

import androidx.room.Database
import androidx.room.Entity
import androidx.room.RoomDatabase

@Database(
    entities = [LearningLeadersEntity::class, SkillsIQLeadersEntity::class],
    version = 1,
    exportSchema = true
)
abstract class RoomCache : RoomDatabase() {
    abstract fun cacheDao() : CacheDao

    
}