package com.example.dogs_app.home.ui

import app.cash.turbine.test
import com.example.dogs_app.MainCoroutineRule
import com.example.dogs_app.data.repository.FakeDogsRepository
import com.example.dogs_app.home.domain.model.Dog
import com.example.dogs_app.home.domain.usecases.GetAllDogsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.not
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DogsViewModelTest {

    private lateinit var repository: FakeDogsRepository
    private lateinit var useCase: GetAllDogsUseCase
    private lateinit var dogsViewModel: DogsViewModel

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun createViewModel() {
        repository = FakeDogsRepository()
        useCase = GetAllDogsUseCase(repository)
        dogsViewModel = DogsViewModel(useCase)
    }

    @Test
    fun `dogsViewModel fetchDogs dataIsNotEmpty`() = runTest {
        dogsViewModel.fetchDogs()
        assertEquals(false, dogsViewModel.uiState.value.dogs.isEmpty())
    }

    @Test
    fun `dogsViewModel fetchDogs returnData`() = runTest {
        dogsViewModel.fetchDogs()
        assertEquals("Coki", dogsViewModel.uiState.value.dogs[0].dogName)
    }

    @Test
    fun `dogsViewModel fetchDogs returnError`() = runTest {
        repository.shouldReturnError = true
        dogsViewModel.fetchDogs()
        MatcherAssert.assertThat(dogsViewModel.uiState.value.userMessage, (not(nullValue())))
        assertEquals("Error at fetching dogs", dogsViewModel.uiState.value.userMessage)
    }
}