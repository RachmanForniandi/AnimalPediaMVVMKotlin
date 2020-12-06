package com.example.animalpediamvvmkotlin.views

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.animalpediamvvmkotlin.R
import com.example.animalpediamvvmkotlin.databinding.FragmentDetailBinding
import com.example.animalpediamvvmkotlin.models.Animal
import com.example.animalpediamvvmkotlin.models.AnimalPalette
import com.example.animalpediamvvmkotlin.utility.getProgressDrawable
import com.example.animalpediamvvmkotlin.utility.loadImage
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_list_data.*
import kotlinx.android.synthetic.main.item_data_animal.*


class DetailFragment : Fragment() {

    var animal: Animal? = null
    private lateinit var dataBinding: FragmentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            animal = DetailFragmentArgs.fromBundle(it).animal
        }

        /*context?.let {
            dataBinding.imgDetailAnimal.loadImage(animal?.imageUrl, getProgressDrawable(it))
        }
*/
        /*tvNameAnimal.text = animal?.name
        tvAnimalLocation.text = animal?.location
        tvAnimalLifeSpan.text = animal?.lifeSpan
        tvAnimalDiet.text = animal?.diet*/

        animal?.imageUrl?.let {
            setupBackgroundColor(it)
        }

        dataBinding.animal = animal

    }

    private fun setupBackgroundColor(url: String) {
        Glide.with(this)
            .asBitmap()
            .load(url)
            .into(object :CustomTarget<Bitmap>(){
                override fun onLoadCleared(placeholder: Drawable?) {

                }

                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    Palette.from(resource)
                        .generate { palette ->
                            val intColor = palette?.lightMutedSwatch?.rgb ?:0
                            //dataBinding.animalDetail.setBackgroundColor(initColor)
                            dataBinding.palette = AnimalPalette(intColor)
                        }
                }

            })
    }

}