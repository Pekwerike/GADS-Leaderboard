package com.prosperekwerike.gadsleaderboard.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.prosperekwerike.gadsleaderboard.models.LearningLeadersCustomModel

class HomepageActivityViewModel(application: Application)
    : AndroidViewModel(application) {

    private val _learningLeadersCollection =
            MutableLiveData<MutableList<LearningLeadersCustomModel>>()
    val learningLeadersCollection : LiveData<MutableList<LearningLeadersCustomModel>>
    get() = _learningLeadersCollection

    fun fetchListOfLearningLeaders(){

        _learningLeadersCollection.value =
    }
}