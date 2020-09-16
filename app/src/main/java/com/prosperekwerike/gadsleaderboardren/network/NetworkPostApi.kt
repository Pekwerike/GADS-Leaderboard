package com.prosperekwerike.gadsleaderboardren.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

const val NETWORK_POST_BASE_URL = "https://docs.google.com/forms/d/e/"

private val network_post_moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val network_post_retrofit_instance = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(network_post_moshi))
    .baseUrl(NETWORK_POST_BASE_URL)
    .build()


interface NetworkPostApiInterface {

    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    @FormUrlEncoded
    fun submitProject(
        @Field("entry.1877115667") firstName: String,
        @Field("entry.2006916086") lastName: String,
        @Field("entry.1824927963") emailAddress: String,
        @Field("entry.284483984") linkToProject: String
    ): Call<Void>
}

object NetworkPostApi {
    val retrofitPostApiService: NetworkPostApiInterface by lazy {
        network_post_retrofit_instance.create(NetworkPostApiInterface::class.java)
    }
}