package com.prosperekwerike.gadsleaderboard.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prosperekwerike.gadsleaderboard.cache.getLocalCache
import com.prosperekwerike.gadsleaderboard.network.NetworkPostApi
import com.prosperekwerike.gadsleaderboard.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.invoke
import kotlinx.coroutines.launch

class SubmissionActivityViewModel(application: Application) : AndroidViewModel(application){

    private val mainRepository = MainRepository(getLocalCache(application).cacheDao())

    fun submitProject(
        firstName : String,
        lastName : String,
        emailAddress : String,
        projectGithubLink : String
    ){
        viewModelScope.launch {
           mainRepository.submitProject(
               firstName = firstName,
               lastName = lastName,
               emailAddress = emailAddress,
               projectGitHubLink = projectGithubLink
           )
        }
    }
}