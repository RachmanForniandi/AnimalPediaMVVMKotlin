package com.example.animalpediamvvmkotlin

import android.app.Application
import com.example.animalpediamvvmkotlin.di.PrefsModule
import com.example.animalpediamvvmkotlin.utility.SharedPreferencesHelper

class PrefsModuleTest(val mockPrefs:SharedPreferencesHelper):PrefsModule() {
    override fun provideSharedPreferences(app: Application): SharedPreferencesHelper {
        return mockPrefs
    }
}