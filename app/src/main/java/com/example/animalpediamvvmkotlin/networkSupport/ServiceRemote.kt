package com.example.animalpediamvvmkotlin.networkSupport

import com.example.animalpediamvvmkotlin.models.Animal
import com.example.animalpediamvvmkotlin.models.ApiKey
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ServiceRemote {

    private val BASE_URL = "https://us-central1-apis-4674e.cloudfunctions.net/"


    var animalService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(AnimalService::class.java)

    fun useApiKey(): Single<ApiKey?>? {
        return animalService.getApiKey()
    }

    fun useAnimalData(key: String): Single<List<Animal>> {
        return animalService.getDataOfAnimals(key)
    }
}