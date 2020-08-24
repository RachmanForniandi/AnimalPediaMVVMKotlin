package com.example.animalpediamvvmkotlin.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.example.animalpediamvvmkotlin.R
import com.example.animalpediamvvmkotlin.models.Animal
import com.example.animalpediamvvmkotlin.utility.getProgressDrawable
import com.example.animalpediamvvmkotlin.utility.loadImage
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_list_data.*
import kotlinx.android.synthetic.main.item_data_animal.*


class DetailFragment : Fragment() {

    var animal: Animal? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            animal = DetailFragmentArgs.fromBundle(it).animal
        }

        context?.let {
            imgDetailAnimal.loadImage(animal?.imageUrl, getProgressDrawable(it))
        }

        tvNameAnimal.text = animal?.name
        tvAnimalLocation.text = animal?.location
        tvAnimalLifeSpan.text = animal?.lifeSpan
        tvAnimalDiet.text = animal?.diet

    }

}