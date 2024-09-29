package com.example.dogs_app.home.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dogs_app.home.data.source.local.dao.DogDao
import com.example.dogs_app.home.data.source.local.entities.DogEntity

@Database(entities = [DogEntity::class], version = 1)
abstract class DogsDatabase: RoomDatabase() {
    abstract fun dogDao(): DogDao

    companion object {
        const val DATABASE_NAME = "dogs_db"
    }
}