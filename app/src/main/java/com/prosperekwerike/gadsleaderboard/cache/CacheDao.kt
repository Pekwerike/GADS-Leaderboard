package com.prosperekwerike.gadsleaderboard.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE

import androidx.room.Query

@Dao
interface CacheDao {
    @Query("SELECT * FROM learning_leaders_table")
    fun getAllLearningLeaders()

    @Query("SELECT * FROM skills_iq_leaders_table")
    fun getAllSkillsIQLeaders()

    @Insert(onConflict = REPLACE)
    fun insertAllLearningLeaders(listOfLearningLeaders : List<LearningLeadersEntity>)

    @Insert(onConflict = REPLACE)
    fun insertAllSkillIQLeaders(listOfSkillsIQLeaders : List<SkillsIQLeadersEntity>)
}