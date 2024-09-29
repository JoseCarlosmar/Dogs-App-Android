package com.example.dogs_app.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogs_app.home.domain.usecases.GetAllDogsUseCase
import com.example.dogs_app.home.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
const val TAG = "HomeViewModel"
@HiltViewModel
class DogsViewModel @Inject constructor(
    private val getAllDogsUseCase: GetAllDogsUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(DogUiState())
    val uiState: StateFlow<DogUiState> = _uiState.asStateFlow()

    fun fetchDogs() {
        viewModelScope.launch {
           _uiState.update { it.copy(isLoading = true) }
            when(val response = getAllDogsUseCase()) {
                is Resource.Error -> _uiState.update {
                    it.copy(
                        isLoading = false,
                        userMessage = response.message!!
                    )
                }
                is Resource.Success -> _uiState.update {
                    it.copy(
                        isLoading = false,
                        userMessage = null,
                        dogs = response.data!!
                    )
                }
            }
        }
    }
}