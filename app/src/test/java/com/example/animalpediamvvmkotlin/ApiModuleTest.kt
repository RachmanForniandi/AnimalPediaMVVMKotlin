package com.example.animalpediamvvmkotlin

import com.example.animalpediamvvmkotlin.di.ApiModule
import com.example.animalpediamvvmkotlin.networkSupport.ServiceRemote
import com.example.animalpediamvvmkotlin.utility.SharedPreferencesHelper

class ApiModuleTest (val mockService: ServiceRemote):ApiModule(){
    override fun provideAnimalServiceRemote(): ServiceRemote {
        return mockService
    }
}