package com.example.recipeapp.presentation.ui

interface FoodCategoryChip{
    fun FoodCategoryChip(category: String, onExecuteSearch: String)
    fun chipChecker(query: String)
}