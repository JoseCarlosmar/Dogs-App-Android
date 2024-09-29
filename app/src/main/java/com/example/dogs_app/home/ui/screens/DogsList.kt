package com.example.dogs_app.home.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dogs_app.home.domain.model.Dog
import com.example.dogs_app.home.ui.components.DogItem

@Composable
fun DogList(
    dogs: List<Dog>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(32.dp),
        contentPadding = PaddingValues(vertical = 24.dp),
        state = rememberLazyListState(),
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        items(items = dogs){ dog ->
            DogItem(dog = dog)
        }
    }
}