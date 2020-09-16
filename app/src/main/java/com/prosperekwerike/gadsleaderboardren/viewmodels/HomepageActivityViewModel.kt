package com.prosperekwerike.gadsleaderboardren.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.prosperekwerike.gadsleaderboardren.cache.getLocalCache
import com.prosperekwerike.gadsleaderboardren.domain.LearningLeadersCustomModel
import com.prosperekwerike.gadsleaderboardren.domain.SkillsIQLeadersCustomModel
import com.prosperekwerike.gadsleaderboardren.repository.MainRepository
import kotlinx.coroutines.launch

class HomepageActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val mainRepository: MainRepository =
        MainRepository(getLocalCache(application).cacheDao())

    val learningLeaders = mainRepository.allLearningLeaders
    val skillIQLeaders = mainRepository.allSkillIQLeaders

    val networkError = mainRepository.networkErrorFromLoading

    fun refreshSkillsIQLeaders() {
        viewModelScope.launch {
            mainRepository.refreshListOfSkillsIQLeaders()
        }
    }

    fun refreshLearningLeaders(){
        viewModelScope.launch {
            mainRepository.refreshListOfLearningLeaders()
        }
    }

}