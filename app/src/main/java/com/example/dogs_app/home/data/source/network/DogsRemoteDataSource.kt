package com.example.dogs_app.home.data.source.network


interface DogsRemoteDataSource {
    suspend fun fetchAllDogs(): List<DogDto>
}