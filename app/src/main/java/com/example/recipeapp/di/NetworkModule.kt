package com.example.recipeapp.di

import com.example.recipeapp.domain.util.DomainMapper
import com.example.recipeapp.network.RecipeService
import com.example.recipeapp.network.model.RecipeDtoMapper
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRecipeDtoMapper() : RecipeDtoMapper {
        return RecipeDtoMapper()
    }

    @Singleton
    @Provides
    fun provideRetrofitService() : RecipeService {
        return Retrofit.Builder()
                .baseUrl("https://food2fork.ca/api/recipe/")
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()//This will create a object of the connection to the api and then
                .create(RecipeService::class.java)//This will help to connect the service to the services we have provided
    }

    @Singleton
    @Provides
    @Named("auth_token")
    fun provideAuthToken(): String {
        return "Token 9c8b06d329136da358c2d00e76946b0111ce2c48"
    }



}