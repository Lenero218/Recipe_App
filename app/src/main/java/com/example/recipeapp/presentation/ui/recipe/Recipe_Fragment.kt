package com.example.recipeapp.presentation.ui.recipe

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.recipeapp.R
import com.example.recipeapp.presentation.ui.recipe_list.RecipeListViewModel
import com.example.recipeapp.presentation.ui.recipe_list.RecipeListViewModel_Factory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_recipe_.*
import kotlinx.android.synthetic.main.fragment_recipe_.image
import kotlinx.android.synthetic.main.recipe_list.*

@AndroidEntryPoint
class Recipe_Fragment : Fragment(R.layout.fragment_recipe_) {

    lateinit var viewModel : RecipeListViewModel
    val args : Recipe_FragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recipe = args.recipe
        Log.d("Fetched Article","Clicked Article is : ${recipe}")

        recipe.featuredImage?.let {
            Glide.with(image)
                .load(it)
                .into(image)
        }
        recipe.publisher?.let{
            AuthorName.text = it
        }
        recipe.rating?.let{
            ratingVal.text = it.toString()
        }
        recipe.description?.let{
            cookingInstructions.text = it
        }



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe_, container, false)
    }


}