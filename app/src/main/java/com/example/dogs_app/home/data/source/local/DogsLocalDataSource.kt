package com.example.dogs_app.home.data.source.local

import com.example.dogs_app.home.data.source.local.entities.DogEntity

interface DogsLocalDataSource {

    suspend fun fetchDogs(): List<DogEntity>

    suspend fun insertAll(dogs: List<DogEntity>)

    suspend fun deleteAll()

}