package com.prosperekwerike.gadsleaderboard.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.prosperekwerike.gadsleaderboard.cache.getLocalCache
import com.prosperekwerike.gadsleaderboard.mappers.convertToLearningLeadersCustomModel
import com.prosperekwerike.gadsleaderboard.mappers.convertToSkillsIQLeadersCustomModel
import com.prosperekwerike.gadsleaderboard.domain.LearningLeadersCustomModel
import com.prosperekwerike.gadsleaderboard.domain.SkillsIQLeadersCustomModel
import com.prosperekwerike.gadsleaderboard.network.NetworkApi
import com.prosperekwerike.gadsleaderboard.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomepageActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val mainRepository : MainRepository = MainRepository(getLocalCache(application).cacheDao())

    private val _learningLeadersCollection =
        MutableLiveData<List<LearningLeadersCustomModel>>()
    val learningLeadersCollection: LiveData<List<LearningLeadersCustomModel>>
        get() = _learningLeadersCollection

    private val _skillsIQLeadersCollection =
        MutableLiveData<List<SkillsIQLeadersCustomModel>>()
    val skillsIQLeadersCollection: LiveData<List<SkillsIQLeadersCustomModel>>
        get() = _skillsIQLeadersCollection


    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                mainRepository.refreshListOfLearningLeaders()
                mainRepository.refreshListOfSkillsIQLeaders()
            }
            withContext(Dispatchers.IO) {
                fetchListOfLearningLeaders()
            }
            withContext(Dispatchers.IO) {
                fetchListOfSkillsIQLeaders()
            }
        }
    }

    private fun fetchListOfLearningLeaders() {
        viewModelScope.launch {
           _learningLeadersCollection.value = mainRepository.allLearningLeaders.value
        }
    }

    private  fun fetchListOfSkillsIQLeaders() {
        viewModelScope.launch {
         _skillsIQLeadersCollection.value = mainRepository.allSkillIQLeaders.value
        }
    }
}