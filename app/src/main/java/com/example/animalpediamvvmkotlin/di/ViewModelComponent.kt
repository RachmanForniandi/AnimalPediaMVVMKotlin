package com.example.animalpediamvvmkotlin.di

import com.example.animalpediamvvmkotlin.viewModels.ListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class,PrefsModule::class,AppModule::class])
interface ViewModelComponent {

    fun inject(viewModel: ListViewModel)
}