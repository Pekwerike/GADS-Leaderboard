package com.prosperekwerike.gadsleaderboard.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.prosperekwerike.gadsleaderboard.mappers.convertToLearningLeadersCustomModel
import com.prosperekwerike.gadsleaderboard.models.LearningLeadersCustomModel
import com.prosperekwerike.gadsleaderboard.models.SkillsIQLeadersCustomModel
import com.prosperekwerike.gadsleaderboard.network.NetworkApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomepageActivityViewModel(application: Application)
    : AndroidViewModel(application) {

    private val _learningLeadersCollection =
            MutableLiveData<MutableList<LearningLeadersCustomModel>>()
    val learningLeadersCollection : LiveData<MutableList<LearningLeadersCustomModel>>
    get() = _learningLeadersCollection

    private val _skillsIQLeadersCollection =
        MutableLiveData<MutableList<SkillsIQLeadersCustomModel>>()
    val skillsIQLeadersCollection : LiveData<MutableList<SkillsIQLeadersCustomModel>>
    get() = _skillsIQLeadersCollection

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                fetchListOfLearningLeaders()
            }
            withContext(Dispatchers.IO){}
            fetchListOfSkillsIQLeaders()
        }
    }

   private fun fetchListOfLearningLeaders() {
        viewModelScope.launch {
            try {
                _learningLeadersCollection.value =
                    NetworkApi.retrofitApiService.getLearningLeader()
                        .convertToLearningLeadersCustomModel().toMutableList()
            }catch (exception : Exception){

            }
        }
    }

    private fun fetchListOfSkillsIQLeaders(){
        try {

        }
    }
}