package com.example.animalpediamvvmkotlin.di
import com.example.animalpediamvvmkotlin.networkSupport.ServiceRemote
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: ServiceRemote)
}