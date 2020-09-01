package com.prosperekwerike.gadsleaderboard.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.prosperekwerike.gadsleaderboard.cache.CacheDao
import com.prosperekwerike.gadsleaderboard.domain.LearningLeadersCustomModel
import com.prosperekwerike.gadsleaderboard.domain.SkillsIQLeadersCustomModel
import com.prosperekwerike.gadsleaderboard.mappers.convertToLearningLeadersCustomModel
import com.prosperekwerike.gadsleaderboard.mappers.convertToLearningLeadersEntity
import com.prosperekwerike.gadsleaderboard.mappers.convertToSkillsIQLeadersCustomModel
import com.prosperekwerike.gadsleaderboard.mappers.convertToSkillsIQLeadersEntity
import com.prosperekwerike.gadsleaderboard.network.NetworkApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepository(private val cacheDao: CacheDao) {


    val allLearningLeaders:
            LiveData<List<LearningLeadersCustomModel>> =
        Transformations.map(cacheDao.getAllLearningLeaders()) {
            it.convertToLearningLeadersCustomModel()
        }

    val allSkillIQLeaders:
            LiveData<List<SkillsIQLeadersCustomModel>> =
        Transformations.map(cacheDao.getAllSkillsIQLeaders()) {
            it.convertToSkillsIQLeadersCustomModel()
        }

    suspend fun refreshListOfLearningLeaders() {
        withContext(Dispatchers.IO) {
            try {
                val learningLeaders =
                    NetworkApi.retrofitApiService.getLearningLeader()
                cacheDao.insertAllLearningLeaders(learningLeaders.convertToLearningLeadersEntity())
            } catch (exception: Exception) {

            }
        }
    }

    suspend fun refreshListOfSkillsIQLeaders() {
        withContext(Dispatchers.IO) {
            try {
                val skillsIQLeaders =
                    NetworkApi.retrofitApiService.getSkillsIQLeaders()
                cacheDao.insertAllSkillIQLeaders(skillsIQLeaders.convertToSkillsIQLeadersEntity())
            } catch (exception: Exception) {

            }
        }
    }

}