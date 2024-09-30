package com.example.dogs_app.home.ui.screens


import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.dogs_app.R
import com.example.dogs_app.home.ui.DogsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: DogsViewModel,
    @StringRes title: Int,
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.fetchDogs()
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        when {
            state.isLoading -> {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
            state.userMessage != null -> {
                Text(
                    text = "${state.userMessage}",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                )

            }
            else -> {
                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(
                            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                titleContentColor = MaterialTheme.colorScheme.onPrimary
                            ),
                            title = {
                                Text(
                                    text = stringResource(id = title)
                                )
                            },
                            navigationIcon = {
                                IconButton(onClick = { }) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = stringResource(id = R.string.top_bar_exit_button)
                                    )
                                }
                            }
                        )
                    }
                ) { contentPadding ->
                    DogList(dogs = state.dogs, paddingValues = contentPadding)
                }
            }
        }
    }
}