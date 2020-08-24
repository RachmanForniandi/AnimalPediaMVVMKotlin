package com.example.animalpediamvvmkotlin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.animalpediamvvmkotlin.R
import com.example.animalpediamvvmkotlin.models.Animal
import com.example.animalpediamvvmkotlin.utility.getProgressDrawable
import com.example.animalpediamvvmkotlin.utility.loadImage
import com.example.animalpediamvvmkotlin.views.ListDataFragmentDirections
import kotlinx.android.synthetic.main.item_data_animal.view.*
import java.util.ArrayList

class AnimalAdapter(private val animalList: ArrayList<Animal>):RecyclerView.Adapter<AnimalAdapter.AnimalHolder>() {

    fun updateAnimalList(newAnimalList: List<Animal>){
        animalList.clear()
        animalList.addAll(newAnimalList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_data_animal,parent,false)
        return AnimalHolder(view)
    }

    override fun getItemCount(): Int {
        return animalList.size
    }

    override fun onBindViewHolder(holder: AnimalHolder, position: Int) {
        holder.itemView.txt_animal_name.text = animalList[position].name
        holder.itemView.img_animal.loadImage(animalList[position].imageUrl,
            getProgressDrawable(holder.itemView.context))
        holder.itemView.item_animal.setOnClickListener {
            val actionToDetail = ListDataFragmentDirections.actionGoToDetails(animalList[position])
            Navigation.findNavController(holder.itemView).navigate(actionToDetail)
        }
    }

    class AnimalHolder (itemView: View):RecyclerView.ViewHolder(itemView){

    }
}