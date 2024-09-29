package com.example.dogs_app.home.data.source.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dogs_app.home.domain.model.Dog

@Entity(tableName = "dogs")
data class DogEntity(
    @PrimaryKey
    var id: String,
    @ColumnInfo(name = "name")
    val dogName: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "age")
    val age: Int,
    @ColumnInfo(name = "url")
    val url: String
) {

}

fun DogEntity.toModel() = Dog(
    id, dogName, description, age, url
)