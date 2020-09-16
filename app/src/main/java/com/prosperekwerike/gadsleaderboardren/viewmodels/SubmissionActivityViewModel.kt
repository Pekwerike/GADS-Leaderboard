package com.prosperekwerike.gadsleaderboardren.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.prosperekwerike.gadsleaderboardren.cache.getLocalCache
import com.prosperekwerike.gadsleaderboardren.repository.MainRepository
import kotlinx.coroutines.launch

class SubmissionActivityViewModel(application: Application) : AndroidViewModel(application){

    private val mainRepository = MainRepository(getLocalCache(application).cacheDao())
    val networkErrorWhileSubmittingProject =
        mainRepository.networkErrorWhileSubmittingProject

    val submissionSuccessfullySignal = mainRepository.projectSubmittedSuccessfullySignal

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