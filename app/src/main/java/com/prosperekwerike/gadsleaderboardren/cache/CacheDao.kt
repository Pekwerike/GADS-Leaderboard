package com.prosperekwerike.gadsleaderboardren.cache

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE

import androidx.room.Query
import androidx.room.Transaction

@Dao
interface CacheDao {
    @Query("SELECT * FROM learning_leaders_table")
    fun getAllLearningLeaders(): LiveData<MutableList<LearningLeadersEntity>>

    @Query("SELECT * FROM skills_iq_leaders_table")
    fun getAllSkillsIQLeaders(): LiveData<MutableList<SkillsIQLeadersEntity>>

    @Insert(onConflict = REPLACE)
    fun insertAllLearningLeaders(listOfLearningLeaders: List<LearningLeadersEntity>)

    @Insert(onConflict = REPLACE)
    fun insertAllSkillIQLeaders(listOfSkillsIQLeaders: List<SkillsIQLeadersEntity>)

    @Transaction
    fun refreshLearningLeaders(listOfLearningLeaders: List<LearningLeadersEntity>) {
        deleteAllLearningLeaders()
        insertAllLearningLeaders(listOfLearningLeaders)
    }

    @Transaction
    fun refreshSkillsIQLeaders(listOfSkillsIQLeaders: List<SkillsIQLeadersEntity>) {
        deleteSkillsIQLeaders()
        insertAllSkillIQLeaders(listOfSkillsIQLeaders)
    }

    @Query("DELETE FROM skills_iq_leaders_table")
    fun deleteSkillsIQLeaders()

    @Query("DELETE FROM learning_leaders_table")
    fun deleteAllLearningLeaders()
}