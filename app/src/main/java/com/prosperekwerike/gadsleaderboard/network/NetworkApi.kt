package com.prosperekwerike.gadsleaderboard.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://gadsapi.herokuapp.com"

val moshi: Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
val retrofit : Retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface NetworkApiInterface {
    @GET("api/hours")
    suspend fun getLearningLeader(): List<LearningLeadersNetworkDataTranferObject>

    @GET("api/skilliq")
    suspend fun getSkillsIQLeaders(): List<SkillsIQLeadersNetworkDataTransferObject>
}

object NetworkApi {
    val retrofitApiService : NetworkApiInterface by lazy{
        retrofit.create(NetworkApiInterface::class.java)
    }
}

