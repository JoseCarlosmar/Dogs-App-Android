package com.example.dogs_app.home.ui

import com.example.dogs_app.home.domain.model.Dog

data class DogUiState(
    val isLoading: Boolean = true,
    val userMessage: String? = null,
    val dogs: List<Dog> = emptyList()
)