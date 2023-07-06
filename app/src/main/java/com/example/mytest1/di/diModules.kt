package com.example.mytest1.di

import android.app.Service
import com.example.mytest1.network.CharacterRepo
import com.example.mytest1.network.CharacterRepoIMpl
import com.example.mytest1.network.ServiceApi
import com.example.mytest1.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)

class diModules{

   @Singleton
   @Provides
   fun providesApiService(): ServiceApi =
       Retrofit.Builder()
           .baseUrl(BASE_URL)
           .addConverterFactory(GsonConverterFactory.create())
           .client(providesHttpService())
           .build()
           .create(ServiceApi::class.java)

    @Singleton
    @Provides
    fun providesHttpService():OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .connectTimeout(30,TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

    @Singleton
    @Provides
    fun providesCharacterRepo():CharacterRepo =
        CharacterRepoIMpl(providesApiService())

}