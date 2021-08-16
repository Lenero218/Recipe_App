package com.example.recipeapp.network.model

import com.example.recipeapp.domain.model.Recipe
import com.example.recipeapp.domain.util.DomainMapper

class RecipeDtoMapper :DomainMapper<RecipeDto,Recipe> {

    //This will convert only individual entity to domain model
    override fun mapToDomainModel(model: RecipeDto): Recipe {
        return Recipe(
            id = model.pk,
            title = model.title,
            featuredImage = model.featuredImage,
            publisher = model.publisher,
            rating = model.rating,
            sourceUrl = model.sourceUrl,
            description = model.description,
            dateAdded = model.date_added,
            dateUpdated = model.date_updated,
            cookingInstructions = model.cooking_instructions,
            ingredients = model.ingredients?: listOf(), //If null then initialise a empty list

        )

    }

    override fun mapFromDomainModel(domainModel: Recipe): RecipeDto {
        return RecipeDto(
            pk = domainModel.id,
            title = domainModel.title,
            featuredImage = domainModel.featuredImage,
            publisher = domainModel.publisher,
            rating = domainModel.rating,
            sourceUrl = domainModel.sourceUrl,
            description = domainModel.description,
            date_added = domainModel.dateAdded,
            date_updated = domainModel.dateUpdated,
            cooking_instructions = domainModel.cookingInstructions,
            ingredients = domainModel.ingredients, //If null then initialise a empty list

        )
    }

    //Functions to convert list of entities to domain model and vice versa
    fun ToDomainList(initial: List<RecipeDto>): List<Recipe> {
        return initial.map { mapToDomainModel(it) }
    }

    fun FromDomainList(initial: List<Recipe>): List<RecipeDto> {
        return initial.map { mapFromDomainModel(it) }
    }



}