package com.example.animalpediamvvmkotlin

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.animalpediamvvmkotlin.di.ApiModule
import com.example.animalpediamvvmkotlin.di.AppModule
import com.example.animalpediamvvmkotlin.di.DaggerViewModelComponent
import com.example.animalpediamvvmkotlin.networkSupport.ServiceRemote
import com.example.animalpediamvvmkotlin.utility.SharedPreferencesHelper
import com.example.animalpediamvvmkotlin.viewModels.ListViewModel
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.concurrent.Executor

class ListViewModelTest {
    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var serviceRemote: ServiceRemote

    @Mock
    lateinit var prefs:SharedPreferencesHelper

    val application = Mockito.mock(Application::class.java)
    var listViewModel = ListViewModel(application,true)

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)

        DaggerViewModelComponent.builder()
            .appModule(AppModule(application) )
            .apiModule(ApiModuleTest(serviceRemote))
            .prefsModule(PrefsModuleTest(prefs))
            .build()
            .inject(listViewModel)
    }

    @Test
    fun getAnimalsSuccess(){

    }


    @Before
    fun setupRxSchedulers(){
        val immediate = object :Scheduler(){
            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(Executor { it.run() },true)
            }
        }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler -> immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> immediate }
    }
}