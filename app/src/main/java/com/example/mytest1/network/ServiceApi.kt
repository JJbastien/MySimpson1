package com.example.mytest1.network

import com.example.mytest1.models.CharacterResponse

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url
import com.example.mytest1.BuildConfig

interface ServiceApi {

    @GET
    suspend fun fetchCharacter(@Url url: String = BuildConfig.ENDPOINT) :Response<CharacterResponse>
}