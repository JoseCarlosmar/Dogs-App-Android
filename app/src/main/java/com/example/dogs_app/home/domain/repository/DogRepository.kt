package com.example.dogs_app.home.domain.repository

import com.example.dogs_app.home.domain.model.Dog
import com.example.dogs_app.home.domain.util.Resource

interface DogRepository {
    suspend fun fetchDogs(): Resource<List<Dog>>
}