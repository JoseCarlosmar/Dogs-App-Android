package com.example.dogs_app.home.data.source.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.dogs_app.home.data.source.local.entities.DogEntity

@Dao
interface DogDao {

    @Query("SELECT * FROM dogs")
    suspend fun fetchDogs(): List<DogEntity>

    @Insert
    suspend fun insertAll(dogs: List<DogEntity>)

    @Query("DELETE FROM dogs")
    suspend fun deleteAll()
}