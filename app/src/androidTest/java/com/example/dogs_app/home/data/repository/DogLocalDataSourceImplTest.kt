package com.example.dogs_app.home.data.repository

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.dogs_app.home.data.source.local.db.DogsDatabase
import com.example.dogs_app.home.data.source.local.entities.DogEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsEqual
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
@MediumTest
class DogLocalDataSourceImplTest {

    private val firstDog = DogEntity("1", "Coki", "He is very playful and eater but very friendly with everyone.", 8, "https://dogs.com/coki/image")
    private val secondDog = DogEntity("2", "Sanzon", "He is very shy and gets afraid of everything. He is playful.", 8, "https://dogs.com/coki/image")

    private lateinit var localDataSource: DogLocalDataSourceImpl
    private lateinit var database: DogsDatabase

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            DogsDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()

        localDataSource = DogLocalDataSourceImpl(database.dogDao())
    }

    @After
    fun cleanUp() = database.close()

    @Test
    fun localDataSourceSaveAndGetDogsReturnData() = runBlocking {
        val dogs = listOf(firstDog, secondDog)
        localDataSource.insertAll(dogs)
        val savedDogs = localDataSource.fetchDogs()
        MatcherAssert.assertThat(savedDogs, IsEqual(dogs))
    }

    @Test
    fun localDataSourceDeleteAndGetDogsReturnEmpty() = runBlocking {
        localDataSource.deleteAll()
        val savedDogs = localDataSource.fetchDogs()
        MatcherAssert.assertThat(savedDogs, IsEqual(emptyList()))
    }
}