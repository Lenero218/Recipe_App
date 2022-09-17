package com.example.recipeapp.repository

import com.example.recipeapp.domain.model.Recipe
import com.example.recipeapp.network.RecipeService
import com.example.recipeapp.network.model.RecipeDtoMapper

class RecipeRepository_Impl(
    private val recipeService: RecipeService,  //Get and Search requests
    private val mapper: RecipeDtoMapper
): RecipeRepository {
    override suspend fun search(token: String, page: Int, query: String): List<Recipe> {
      val result = recipeService.search(token, page, query).recipes //This will return all the recipes network entities
        return mapper.ToDomainList(result)
    }

    override suspend fun get(token: String, id: Int): Recipe {
    val recipe = recipeService.get(token, id)
        return mapper.mapToDomainModel(recipe)
    }

}