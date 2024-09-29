package com.example.dogs_app.home.data.repository

import com.example.dogs_app.home.data.source.network.DogDto
import com.example.dogs_app.home.data.source.network.DogsRemoteDataSource
import com.example.dogs_app.home.data.source.network.api.DogsService
import javax.inject.Inject

class DogRemoteDataSourceImpl @Inject constructor(
    private val service: DogsService
): DogsRemoteDataSource {

    override suspend fun fetchAllDogs(): List<DogDto> {
        return service.fetchDogs()
    }
}