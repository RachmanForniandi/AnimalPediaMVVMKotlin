package com.example.animalpediamvvmkotlin.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.example.animalpediamvvmkotlin.R
import kotlinx.android.synthetic.main.fragment_list_data.*


class ListDataFragment : Fragment() {
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

    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fab_1.setOnClickListener {
            val actionToDetail:NavDirections = ListDataFragmentDirections.actionGoToDetails()
            Navigation.findNavController(it).navigate(actionToDetail)
        }
    }*/

}