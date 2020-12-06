package com.example.animalpediamvvmkotlin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.animalpediamvvmkotlin.R
import com.example.animalpediamvvmkotlin.databinding.ItemDataAnimalBinding
import com.example.animalpediamvvmkotlin.models.Animal
import com.example.animalpediamvvmkotlin.views.ListDataFragmentDirections
import java.util.ArrayList

class AnimalAdapter(private val animalList: ArrayList<Animal>):RecyclerView.Adapter<AnimalAdapter.AnimalHolder>() , AnimalClickListener{

    fun updateAnimalList(newAnimalList: List<Animal>){
        animalList.clear()
        animalList.addAll(newAnimalList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalHolder {
        val view = DataBindingUtil.inflate<ItemDataAnimalBinding>(LayoutInflater.from(parent.context),R.layout.item_data_animal,parent,false)
        return AnimalHolder(view)
    }

    override fun getItemCount(): Int {
        return animalList.size
    }

    override fun onBindViewHolder(holder: AnimalHolder, position: Int) {

        holder.view.animal = animalList[position]
        holder.view.listener = this

    }

    override fun onClick(v: View){
        for(animal in animalList){
            if (v.tag == animal.name){
                val actionToDetail = ListDataFragmentDirections.actionGoToDetails(animal)
                Navigation.findNavController(v).navigate(actionToDetail)
            }
        }
    }

    class AnimalHolder (var view: ItemDataAnimalBinding): RecyclerView.ViewHolder(view.root){

    }
}