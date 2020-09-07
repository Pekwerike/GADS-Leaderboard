package com.prosperekwerike.gadsleaderboard.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.prosperekwerike.gadsleaderboard.cache.CacheDao
import com.prosperekwerike.gadsleaderboard.domain.LearningLeadersCustomModel
import com.prosperekwerike.gadsleaderboard.domain.SkillsIQLeadersCustomModel
import com.prosperekwerike.gadsleaderboard.mappers.convertToLearningLeadersCustomModel
import com.prosperekwerike.gadsleaderboard.mappers.convertToLearningLeadersEntity
import com.prosperekwerike.gadsleaderboard.mappers.convertToSkillsIQLeadersCustomModel
import com.prosperekwerike.gadsleaderboard.mappers.convertToSkillsIQLeadersEntity
import com.prosperekwerike.gadsleaderboard.network.NetworkApi
import com.prosperekwerike.gadsleaderboard.network.NetworkPostApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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

    private val _networkErrorFromLoading = MutableLiveData<Boolean>()
    val networkErrorFromLoading : LiveData<Boolean>
    get() = _networkErrorFromLoading

    private val _networkErrorWhileSubmittingProject = MutableLiveData<Boolean>()
    val networkErrorWhileSubmittingProject : LiveData<Boolean>
    get() = _networkErrorWhileSubmittingProject

    suspend fun refreshListOfLearningLeaders() {
        withContext(Dispatchers.IO) {
            try {
                val learningLeaders =
                    NetworkApi.retrofitApiService.getLearningLeader()
                cacheDao.refreshLearningLeaders(learningLeaders.convertToLearningLeadersEntity())
            } catch (exception: Exception) {
                //no internet or network error
                withContext(Dispatchers.Main){
                    _networkErrorFromLoading.value = true
                }
            }
        }
    }

    suspend fun refreshListOfSkillsIQLeaders() {
        withContext(Dispatchers.IO) {
            try {
                val skillsIQLeaders =
                    NetworkApi.retrofitApiService.getSkillsIQLeaders()
                cacheDao.refreshSkillsIQLeaders(skillsIQLeaders.convertToSkillsIQLeadersEntity())
            } catch (exception: Exception) {
                withContext(Dispatchers.Main){
                    _networkErrorFromLoading.value = true
                }
            }
        }
    }

    suspend fun submitProject(
        firstName : String,
        lastName : String,
        emailAddress : String,
        projectGitHubLink : String
    ){
        withContext(Dispatchers.IO){
            try {
                NetworkPostApi.retrofitPostApiService.submitProject(
                    firstName = firstName,
                    lastName = lastName,
                    emailAddress = emailAddress,
                    linkToProject = projectGitHubLink
                )

            }catch (exception: Exception){
                //notify the user that the process to submit video failed
                withContext(Dispatchers.Main){
                    _networkErrorWhileSubmittingProject.value = true
                }
            }
        }
    }

}