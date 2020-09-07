package com.prosperekwerike.gadsleaderboard.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prosperekwerike.gadsleaderboard.network.NetworkPostApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.invoke
import kotlinx.coroutines.launch

class SubmissionActivityViewModel() : ViewModel(){


    fun submitProject(
        firstName : String,
        lastName : String,
        emailAddress : String,
        projectGithubLink : String
    ){
        viewModelScope.launch {
            Dispatchers.IO{
                NetworkPostApi.retrofitPostApiService.submitProject(
                    firstName = firstName,
                    lastName = lastName,
                    emailAddress = emailAddress,
                    linkToProject = projectGithubLink
                )
            }
        }
    }
}