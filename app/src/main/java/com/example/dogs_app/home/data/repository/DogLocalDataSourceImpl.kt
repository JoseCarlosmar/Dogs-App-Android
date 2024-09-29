package com.example.dogs_app.home.data.repository

import com.example.dogs_app.home.data.source.local.DogsLocalDataSource
import com.example.dogs_app.home.data.source.local.dao.DogDao
import com.example.dogs_app.home.data.source.local.entities.DogEntity
import com.example.dogs_app.home.domain.model.Dog
import javax.inject.Inject

class DogLocalDataSourceImpl @Inject constructor(
    private val dogDao: DogDao
): DogsLocalDataSource {

    override suspend fun fetchDogs(): List<DogEntity> {
        return dogDao.fetchDogs()
    }

    override suspend fun insertAll(dogs: List<DogEntity>) {
        dogDao.insertAll(dogs)
    }

    override suspend fun deleteAll() {
        dogDao.deleteAll()
    }
}