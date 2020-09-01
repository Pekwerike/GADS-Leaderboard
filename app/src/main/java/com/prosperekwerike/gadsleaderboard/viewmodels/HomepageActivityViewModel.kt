package com.prosperekwerike.gadsleaderboard.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.prosperekwerike.gadsleaderboard.cache.getLocalCache
import com.prosperekwerike.gadsleaderboard.domain.LearningLeadersCustomModel
import com.prosperekwerike.gadsleaderboard.domain.SkillsIQLeadersCustomModel
import com.prosperekwerike.gadsleaderboard.repository.MainRepository
import kotlinx.coroutines.launch

class HomepageActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val mainRepository: MainRepository =
        MainRepository(getLocalCache(application).cacheDao())

    val learningLeaders = mainRepository.allLearningLeaders
    val skillIQLeaders = mainRepository.allSkillIQLeaders

    fun refreshSkillsIQLeaders() {
        viewModelScope.launch {
            mainRepository.refreshListOfSkillsIQLeaders()
        }
    }

}