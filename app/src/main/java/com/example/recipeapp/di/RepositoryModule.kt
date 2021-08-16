package com.example.recipeapp.di

import com.example.recipeapp.network.RecipeService
import com.example.recipeapp.network.model.RecipeDtoMapper
import com.example.recipeapp.repository.RecipeRepository
import com.example.recipeapp.repository.RecipeRepository_Impl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRecipeRepository(
            recipeService: RecipeService, //Since we have previously instantiated them as dependency thats wy hilt is able to understand where to get them
            recipeDtoMapper: RecipeDtoMapper
    ): RecipeRepository{
        return RecipeRepository_Impl(
                recipeService,recipeDtoMapper
        )
    }


}