package com.example.dogs_app.home.data.source.network.api

import com.example.dogs_app.home.data.source.network.DogDto
import retrofit2.http.GET

interface DogsService {

    @GET("api/1151549092634943488")
    suspend fun fetchDogs(): List<DogDto>
}