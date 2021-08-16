package com.example.recipeapp.presentation.ui.recipe_list


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.domain.model.Recipe
import com.example.recipeapp.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.function.BiPredicate
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class RecipeListViewModel
@Inject
//Providing previous dependencies as constructor asguments so that we can use them in current class
constructor(
    private @Named("auth_token") val token : String,
    private val Repository: RecipeRepository, //This way we have provided as dependency to the ViewModel

): ViewModel(){

                private val _recipes : MutableLiveData<List<Recipe>> = MutableLiveData()
                val recipes : LiveData<List<Recipe>> get() = _recipes //This is only read only type not going to chage
                val _query : MutableLiveData<String> = MutableLiveData()
                val query : LiveData<String> get() = _query
                val _loading : MutableLiveData<Boolean> = MutableLiveData()
                val loading: LiveData<Boolean> get() = _loading



   init {

            newSearch("")
    }

   fun newSearch(query : String) {
          viewModelScope .launch {
            _loading.value = true

            val result = Repository.search(
                    token = token,
                    page = 1,
                    query = query
            )
            _recipes.value = result

            _loading.value = false

        }

    }



    fun onQueryChanged(query : String){
        this._query.value = query
    }




}