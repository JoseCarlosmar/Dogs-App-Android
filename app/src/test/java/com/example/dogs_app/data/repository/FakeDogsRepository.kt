package com.example.dogs_app.data.repository

import com.example.dogs_app.home.domain.model.Dog
import com.example.dogs_app.home.domain.repository.DogRepository
import com.example.dogs_app.home.domain.util.Resource

class FakeDogsRepository: DogRepository  {

    var shouldReturnError = false

    override suspend fun fetchDogs(): Resource<List<Dog>> {
        return if(shouldReturnError) {
            Resource.Error("Error at fetching dogs")
        } else {
            Resource.Success(
                listOf(
                    Dog(
                        "1",
                        "Coki",
                        "He is very playful and eater but very friendly with everyone.",
                        8,
                        "https://dogs.com/coki/image"
                    )
                )
            )
        }
    }
}