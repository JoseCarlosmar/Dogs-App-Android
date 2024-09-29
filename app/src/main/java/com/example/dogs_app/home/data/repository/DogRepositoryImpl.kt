package com.example.dogs_app.home.data.repository

import android.util.Log
import coil.network.HttpException
import com.example.dogs_app.home.data.source.local.DogsLocalDataSource
import com.example.dogs_app.home.data.source.local.entities.toModel
import com.example.dogs_app.home.data.source.network.DogsRemoteDataSource
import com.example.dogs_app.home.data.source.network.toEntity
import com.example.dogs_app.home.domain.model.Dog
import com.example.dogs_app.home.domain.repository.DogRepository
import com.example.dogs_app.home.domain.util.Resource
import okio.IOException
import javax.inject.Inject
const val TAG = "DogRepository"
class DogRepositoryImpl @Inject constructor(
    private val localDataSource: DogsLocalDataSource,
    private val remoteDataSource: DogsRemoteDataSource
): DogRepository {

    override suspend fun fetchDogs(): Resource<List<Dog>> {
        return try {
            val localDogs = localDataSource.fetchDogs()
            if(localDogs.isEmpty()) {
                val remoteDogs = remoteDataSource.fetchAllDogs()
                localDataSource.insertAll(remoteDogs.map { it.toEntity() })
            }
            Resource.Success(localDataSource.fetchDogs().map { it.toModel() })
        } catch (e: Exception) {
            Resource.Error("Error trying to fetch dogs: ${e.message}")
        }
    }
}