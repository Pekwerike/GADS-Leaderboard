package com.prosperekwerike.gadsleaderboard.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.prosperekwerike.gadsleaderboard.mappers.convertToLearningLeadersCustomModel
import com.prosperekwerike.gadsleaderboard.models.LearningLeadersCustomModel
import com.prosperekwerike.gadsleaderboard.network.NetworkApi
import kotlinx.coroutines.launch

class HomepageActivityViewModel(application: Application)
    : AndroidViewModel(application) {

    private val _learningLeadersCollection =
            MutableLiveData<MutableList<LearningLeadersCustomModel>>()
    val learningLeadersCollection : LiveData<MutableList<LearningLeadersCustomModel>>
    get() = _learningLeadersCollection

    init {
        fetchListOfLearningLeaders()
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
}