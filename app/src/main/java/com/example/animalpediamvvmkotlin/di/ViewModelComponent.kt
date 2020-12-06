package com.example.animalpediamvvmkotlin.di

import com.example.animalpediamvvmkotlin.viewModels.ListViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ViewModelComponent {

    fun inject(viewModel: ListViewModel)
}