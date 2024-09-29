package com.example.dogs_app.home.di

import com.example.dogs_app.home.data.source.local.db.DogsDatabase
import com.example.dogs_app.home.data.source.network.api.DogsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object HomeModule {

    @ViewModelScoped
    @Provides
    fun provideDogService(
        retrofit: Retrofit
    ): DogsService = retrofit.create(DogsService::class.java)

    @ViewModelScoped
    @Provides
    fun provideDogDao(
        database: DogsDatabase
    ) = database.dogDao()
}