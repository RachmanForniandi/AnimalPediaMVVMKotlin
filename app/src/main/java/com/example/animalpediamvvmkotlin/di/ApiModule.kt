package com.example.animalpediamvvmkotlin.di

import com.example.animalpediamvvmkotlin.networkSupport.AnimalService
import com.example.animalpediamvvmkotlin.networkSupport.ServiceRemote
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
open class ApiModule {
    private val BASE_URL = "https://us-central1-apis-4674e.cloudfunctions.net/"

    @Provides
    fun provideAnimalApi():AnimalService{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(AnimalService::class.java)
    }

    @Provides
    open fun provideAnimalServiceRemote():ServiceRemote{
        return ServiceRemote()
    }



}