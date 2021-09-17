package com.example.recipeapp.presentation.ui.recipe_list

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.R
import com.example.recipeapp.presentation.ui.Adapter
import com.example.recipeapp.presentation.ui.Animations.CircularIndeterminateProgressBar
import com.example.recipeapp.presentation.ui.Animations.ShimmerEffect
import com.example.recipeapp.presentation.ui.FoodCategoryChip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_recipe_list.*

@AndroidEntryPoint
class RecipeListFragment : Fragment(),FoodCategoryChip, CircularIndeterminateProgressBar,ShimmerEffect {
    private lateinit var recyclerAdapter: Adapter


    val viewModel: RecipeListViewModel by viewModels()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu,menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


       val theme = when(item.itemId){
            R.id.dark -> {
                Log.d("Tag","Dark mode attached")
                AppCompatDelegate.MODE_NIGHT_YES
            }
            R.id.light -> {

                Log.d("Tag", "Light mode attached")
                AppCompatDelegate.MODE_NIGHT_NO
            }

            else ->AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        }


        AppCompatDelegate.setDefaultNightMode(theme)

        return super.onOptionsItemSelected(item)

    }



    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        Log.d("Tag", "OnCreateView: ${viewModel}")


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe_list, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.inflateMenu(R.menu.menu)//To inflate the toolbar with Menu items
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)

        viewModel.loading.observe(viewLifecycleOwner,{
            Log.d("Tag", "Loading value: ${viewModel.loading.value}")

            CircularIndeterminateProgressBarDisplayed(isDisplay = it)

            IsShimmerEffectOn(isDisplayed = it)

        })



        //Setting the adapter
        viewModel.recipes.observe(viewLifecycleOwner, { recipes ->
            //   In this we will write the whole code to get the data
            for (recipe in recipes) {
                Log.d("Tag", "onViewCreated: ${recipe.rating}")
            }
            recycler_View.apply {
                layoutManager = LinearLayoutManager(activity)
                recyclerAdapter = Adapter(recipes)
//                val topSpacingItemDecoration = TopSpacingItemDecoration(30)
//                addItemDecoration(topSpacingItemDecoration)
                adapter = recyclerAdapter
//                viewModel.onChangeRecipeScrollPosition(recyclerAdapter.postion)





            }

        })





        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {


            override fun onQueryTextSubmit(query: String?): Boolean {

                //To reach to the right chip
                query?.let { chipChecker(it) }
                if (query != null) {
                    viewModel.newSearch(query)
                } else {
                    viewModel.newSearch(query = " ")
                }

                return false

            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    viewModel.onQueryChanged(newText)

                } else {
                    viewModel.newSearch(query = "")
                }
                return false
            }

        })

                Burgers.setOnClickListener {
                    FoodCategoryChip("Burgers", onExecuteSearch = "Burgers")
                }

               Chicken.setOnClickListener {
                   FoodCategoryChip("Chicken","Chicken")
               }

                Dessert.setOnClickListener {
                    FoodCategoryChip("Dessert", onExecuteSearch = "Dessert")
                }

                ice_cream.setOnClickListener {
                    FoodCategoryChip("Ice Cream", onExecuteSearch = "Ice Cream")
                }

                vegetarian.setOnClickListener {
                    FoodCategoryChip("Vegetarian", onExecuteSearch = "Vegetarian")
                }

                loaf.setOnClickListener {
                    FoodCategoryChip("Loaf", onExecuteSearch = "Loaf")
                }

                Beef.setOnClickListener {
                    FoodCategoryChip("Beef", onExecuteSearch = "Beef")
                }

    }






    override fun FoodCategoryChip(category: String, onExecuteSearch: String) {
        Log.d("Tag","Food Category: ${category}+${onExecuteSearch}")
        viewModel.onQueryChanged(category)
        search_view.setQuery(category, true)

    }

    override fun chipChecker(query: String) {
    if(query == "Dessert") {

        if (Dessert.isChecked == true){
                        return
        }
        else{

            cgSort.clearCheck()
            Dessert.isChecked = true

        }
    }
       else if(query == "Chicken") {

            if (Chicken.isChecked == true){
                return
            }
            else{

                cgSort.clearCheck()
                Chicken.isChecked = true

            }
        }
       else if(query == "Beef") {

            if (Beef.isChecked == true){
                return
            }
            else{

                cgSort.clearCheck()
                Beef.isChecked = true

            }
        }
       else if(query == "Vegetarian") {

            if (vegetarian.isChecked == true){
                return
            }
            else{

                cgSort.clearCheck()
                vegetarian.isChecked = true

            }
        }
       else if(query == "Loaf") {

            if (loaf.isChecked == true){
                return
            }
            else{

                cgSort.clearCheck()
                loaf.isChecked = true

            }
        }
        else if(query == "Ice Cream") {

            if (ice_cream.isChecked == true){
                return
            }
            else{

                cgSort.clearCheck()
                ice_cream.isChecked = true

            }
        }

       else  if(query == "Burgers") {

            if (Burgers.isChecked == true){
                return
            }
            else{

                cgSort.clearCheck()
                Burgers.isChecked = true

            }
        }
        else {
        cgSort.clearCheck()
         }

    }

    override fun CircularIndeterminateProgressBarDisplayed(isDisplay: Boolean) {
       val progressBar : ProgressBar = progressBar

        if (isDisplay == true){
            progressBar.visibility = View.VISIBLE
        }
        else if(isDisplay == false) {
            progressBar.visibility = View.GONE
        }
    }

    override fun IsShimmerEffectOn(isDisplayed: Boolean) {
        if(isDisplayed){
            //Starting the shimmering effect
            shimmer_view_container.startShimmer()
            shimmer_view_container.visibility = View.VISIBLE
        }
        else{
            //Closing the shimmering effect
            shimmer_view_container.stopShimmer()
            shimmer_view_container.visibility = View.GONE
        }
    }

        private fun setRecyclerViewScrollListener() {
            val scrollListener = object : RecyclerView.OnScrollListener(){
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    val totalItemCount = recyclerView!!.layoutManager?.itemCount
                    viewModel.onChangeRecipeScrollPosition(totalItemCount!!)
                   //Complete this function using coding with mitch
                    
                }
            }
        }

}