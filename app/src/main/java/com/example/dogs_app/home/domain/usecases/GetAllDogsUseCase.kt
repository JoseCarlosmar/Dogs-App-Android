package com.example.dogs_app.home.domain.usecases

import com.example.dogs_app.home.domain.model.Dog
import com.example.dogs_app.home.domain.repository.DogRepository
import com.example.dogs_app.home.domain.util.Resource
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GetAllDogsUseCase @Inject constructor(
    private val repository: DogRepository
) {

    suspend operator fun invoke(): Resource<List<Dog>> {
        return repository.fetchDogs()
    }
}