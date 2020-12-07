package com.example.animalpediamvvmkotlin

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.animalpediamvvmkotlin.di.ApiModule
import com.example.animalpediamvvmkotlin.di.AppModule
import com.example.animalpediamvvmkotlin.di.DaggerViewModelComponent
import com.example.animalpediamvvmkotlin.models.Animal
import com.example.animalpediamvvmkotlin.models.ApiKey
import com.example.animalpediamvvmkotlin.networkSupport.ServiceRemote
import com.example.animalpediamvvmkotlin.utility.SharedPreferencesHelper
import com.example.animalpediamvvmkotlin.viewModels.ListViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Assert
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

    private val key = "Test key"

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
        Mockito.`when`(prefs.fetchApiKey()).thenReturn(key)
        val animal = Animal("bear",null,null,null,null,null,null)
        val animalList = listOf(animal)

        val testSingle = Single.just(animalList)

        Mockito.`when`(serviceRemote.useAnimalData(key)).thenReturn(testSingle)

        listViewModel.refresh()

        Assert.assertEquals(1,listViewModel.animals.value?.size)
        Assert.assertEquals(false,listViewModel.loadError.value)
        Assert.assertEquals(false,listViewModel.loading.value)
    }

    @Test
    fun getAnimalsFailure(){
        Mockito.`when`(prefs.fetchApiKey()).thenReturn(key)
        val testSingle = Single.error<List<Animal>>(Throwable())
        val keySingle = Single.just(ApiKey("OK",key))


        Mockito.`when`(serviceRemote.useAnimalData(key)).thenReturn(testSingle)
        Mockito.`when`(serviceRemote.useApiKey()).thenReturn(keySingle)

        listViewModel.refresh()

        Assert.assertEquals(null,listViewModel.animals.value?.size)
        Assert.assertEquals(false,listViewModel.loadError.value)
        Assert.assertEquals(true,listViewModel.loading.value)
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