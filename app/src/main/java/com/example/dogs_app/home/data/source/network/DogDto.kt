package com.example.dogs_app.home.data.source.network

import com.example.dogs_app.home.data.source.local.entities.DogEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class DogDto(
    val id: String = UUID.randomUUID().toString(),
    @SerialName("dogName")
    val dogName: String,
    @SerialName("description")
    val description: String,
    @SerialName("age")
    val age: Int,
    @SerialName("image")
    val image: String
)

fun DogDto.toEntity() = DogEntity(
    id, dogName, description, age, image
)
