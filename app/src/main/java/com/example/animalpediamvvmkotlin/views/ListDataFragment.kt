package com.example.animalpediamvvmkotlin.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.animalpediamvvmkotlin.R
import com.example.animalpediamvvmkotlin.adapters.AnimalAdapter
import com.example.animalpediamvvmkotlin.models.Animal
import com.example.animalpediamvvmkotlin.viewModels.ListViewModel
import kotlinx.android.synthetic.main.fragment_list_data.*


class ListDataFragment : Fragment() {

    private lateinit var viewModel:ListViewModel
    private val animalAdapter = AnimalAdapter(arrayListOf())

    private val animalListDataObserver = Observer<List<Animal>>{ list ->
        list?.let {
            animal_list.visibility = View.VISIBLE
            animalAdapter.updateAnimalList(it)
        }
    }

    private val loadingLiveDataObserver = Observer<Boolean> { isLoading ->
        loading_indicator.visibility = if (isLoading)View.VISIBLE else View.GONE
        if (isLoading){
            txt_error.visibility = View.GONE
            animal_list.visibility = View.GONE
        }
    }

    private val errorLiveDataObserver = Observer<Boolean> { isError ->
        txt_error.visibility = if (isError)View.VISIBLE else View.GONE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.animals.observe(viewLifecycleOwner,animalListDataObserver)
        viewModel.loading.observe(viewLifecycleOwner,loadingLiveDataObserver)
        viewModel.loadError.observe(viewLifecycleOwner,errorLiveDataObserver)
        viewModel.refresh()

        animal_list.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter = animalAdapter
        }
    }

}