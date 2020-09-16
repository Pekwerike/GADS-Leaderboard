package com.prosperekwerike.gadsleaderboardren.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.prosperekwerike.gadsleaderboardren.cache.CacheDao
import com.prosperekwerike.gadsleaderboardren.domain.LearningLeadersCustomModel
import com.prosperekwerike.gadsleaderboardren.domain.SkillsIQLeadersCustomModel
import com.prosperekwerike.gadsleaderboardren.mappers.convertToLearningLeadersCustomModel
import com.prosperekwerike.gadsleaderboardren.mappers.convertToLearningLeadersEntity
import com.prosperekwerike.gadsleaderboardren.mappers.convertToSkillsIQLeadersCustomModel
import com.prosperekwerike.gadsleaderboardren.mappers.convertToSkillsIQLeadersEntity
import com.prosperekwerike.gadsleaderboardren.network.NetworkApi
import com.prosperekwerike.gadsleaderboardren.network.NetworkPostApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
    val networkErrorFromLoading: LiveData<Boolean>
        get() = _networkErrorFromLoading

    private val _networkErrorWhileSubmittingProject = MutableLiveData<Boolean>()
    val networkErrorWhileSubmittingProject: LiveData<Boolean>
        get() = _networkErrorWhileSubmittingProject

    private val _projectSubmittedSuccessfullySignal = MutableLiveData<Boolean>()
    val projectSubmittedSuccessfullySignal: LiveData<Boolean>
        get() = _projectSubmittedSuccessfullySignal

    suspend fun refreshListOfLearningLeaders() {
        withContext(Dispatchers.IO) {
            try {
                val learningLeaders =
                    NetworkApi.retrofitApiService.getLearningLeader()
                cacheDao.refreshLearningLeaders(learningLeaders.convertToLearningLeadersEntity())
            } catch (exception: Exception) {
                //no internet or network error
                withContext(Dispatchers.Main) {
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
                withContext(Dispatchers.Main) {
                    _networkErrorFromLoading.value = true
                }
            }
        }
    }

    suspend fun submitProject(
        firstName: String,
        lastName: String,
        emailAddress: String,
        projectGitHubLink: String
    ) {
        withContext(Dispatchers.Main) {
            try {
            NetworkPostApi.retrofitPostApiService.submitProject(
                    firstName = firstName,
                    lastName = lastName,
                    emailAddress = emailAddress,
                    linkToProject = projectGitHubLink
                ).enqueue(object : Callback<Void> {
                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        _networkErrorWhileSubmittingProject.value = true

                    }

                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        //use live data to signify that the project was submitted successfully
                        _projectSubmittedSuccessfullySignal.value = true

                    }

                })

            } catch (exception: Exception) {
                //notify the user that the process to submit video failed
                    _networkErrorWhileSubmittingProject.value = true

            }
        }
    }

}