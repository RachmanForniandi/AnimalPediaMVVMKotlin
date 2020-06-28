package com.example.animalpediamvvmkotlin.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.animalpediamvvmkotlin.models.Animal

class ListViewModel(application: Application): AndroidViewModel(application) {
    val animals by lazy { MutableLiveData<List<Animal>>() }
    val loadError by lazy { MutableLiveData<Boolean>() }
    val loading by lazy { MutableLiveData<Boolean>() }

    fun refresh(){
        obtainDataAnimals()
    }

    private fun obtainDataAnimals() {
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






    }

}