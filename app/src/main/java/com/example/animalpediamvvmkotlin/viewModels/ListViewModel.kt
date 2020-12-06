package com.example.animalpediamvvmkotlin.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.animalpediamvvmkotlin.di.ApiModule_ProvideAnimalServiceRemoteFactory.create
import com.example.animalpediamvvmkotlin.di.AppModule
import com.example.animalpediamvvmkotlin.di.DaggerApiComponent.create
import com.example.animalpediamvvmkotlin.di.DaggerViewModelComponent
import com.example.animalpediamvvmkotlin.models.Animal
import com.example.animalpediamvvmkotlin.models.ApiKey
import com.example.animalpediamvvmkotlin.networkSupport.ServiceRemote
import com.example.animalpediamvvmkotlin.utility.SharedPreferencesHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListViewModel(application: Application): AndroidViewModel(application) {

    val animals by lazy { MutableLiveData<List<Animal>>() }
    val loadError by lazy { MutableLiveData<Boolean>() }
    val loading by lazy { MutableLiveData<Boolean>() }

    private val disposable = CompositeDisposable()

    @Inject
    lateinit var api :ServiceRemote

    @Inject
    lateinit var sharedPreferences:SharedPreferencesHelper

    private var invalidApiKey = false
    //private var injected = false
/*
    fun inject(){
        if (!injected){
            DaggerViewModelComponent.builder()
                .appModule(AppModule(getApplication()))
                .build()
                .inject(this)
        }
    }*/

    init {
        DaggerViewModelComponent.builder()
            .appModule(AppModule(getApplication()))
            .build()
            .inject(this)
    }


    fun refresh(){
        //obtainDataAnimals()
        loading.value = true
        invalidApiKey = false
        val itemKey = sharedPreferences.fetchApiKey()
        if (itemKey.isNullOrEmpty()){
            obtainKey()
        }else{
            obtainDataAnimals(itemKey)
        }

    }

    fun hardRefresh(){
        loading.value = true
        obtainKey()
    }

    private fun obtainKey() {
        disposable.add(
                api.useApiKey()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableSingleObserver<ApiKey>(){
                            override fun onSuccess(key: ApiKey) {
                                if (key.key.isNullOrEmpty()){
                                    loadError.value = true
                                    loading.value = false
                                }else{
                                    sharedPreferences.saveApiKey(key.key)
                                    obtainDataAnimals(key.key)
                                }
                            }

                            override fun onError(e: Throwable) {
                                e.printStackTrace()
                                loading.value = false
                                loadError.value = true
                            }
                        })
        )
    }

    /*private fun obtainDataAnimals() {
        val data1 = Animal("Cat")
        val data2 = Animal("Dog")
        val data3 = Animal("Parrot")
        val data4 = Animal("Squirrel")
        val data5 = Animal("Elephant")
        val data6 = Animal("Ox")
        val data7 = Animal("tiger")

        val animalList = arrayListOf(data1,data2,data3,data4,data5,data6,data7)
        animals.value = animalList
        loadError.value = false
        loading.value = false

    }*/

    private fun obtainDataAnimals(key:String) {
        Log.d("ListViewModel", "obtainDataAnimals Called")
        disposable.add(
                api.useAnimalData(key)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object :DisposableSingleObserver<List<Animal>>(){
                            override fun onSuccess(list: List<Animal>) {
                                loadError.value = false
                                animals.value = list
                                loading.value = false

                            }

                            override fun onError(e: Throwable) {
                                if (!invalidApiKey){
                                    invalidApiKey = true
                                    obtainKey()
                                }else{
                                    e.printStackTrace()
                                    loading.value = false
                                    animals.value = null
                                    loadError.value = true
                                }

                            }
                        })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }



}