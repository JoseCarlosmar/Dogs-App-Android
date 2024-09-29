package com.example.dogs_app.di

import android.content.Context
import androidx.room.Room
import com.example.dogs_app.home.data.source.local.db.DogsDatabase
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideEndpoint() = "https://jsonblob.com/"

    @Provides
    @Singleton
    fun provideRetrofit(
        endpoint: String
    ): Retrofit = Retrofit.Builder()
        .baseUrl(endpoint)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .client(
            OkHttpClient.Builder().addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    HttpLoggingInterceptor.Level.BODY
                )
            ).build()
        )
        .build()

    @Provides
    @Singleton
    fun provideDogsDatabase(
        @ApplicationContext context: Context
    ): DogsDatabase = Room.databaseBuilder(
        context, DogsDatabase::class.java, DogsDatabase.DATABASE_NAME
    ).build()
}