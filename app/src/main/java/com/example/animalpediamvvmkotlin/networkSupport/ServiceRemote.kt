package com.example.animalpediamvvmkotlin.networkSupport

import com.example.animalpediamvvmkotlin.di.DaggerApiComponent
import com.example.animalpediamvvmkotlin.models.Animal
import com.example.animalpediamvvmkotlin.models.ApiKey
import io.reactivex.Single
import javax.inject.Inject

class ServiceRemote {

    @Inject
    lateinit var api :AnimalService

    init {
        DaggerApiComponent.create().inject(this)

    }

    fun useApiKey(): Single<ApiKey> {
        return api.getApiKey()
    }

    fun useAnimalData(key: String): Single<List<Animal>> {
        return api.getDataOfAnimals(key)
    }
}