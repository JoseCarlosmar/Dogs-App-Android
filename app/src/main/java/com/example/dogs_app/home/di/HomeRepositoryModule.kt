package com.example.dogs_app.home.di

import com.example.dogs_app.home.data.repository.DogLocalDataSourceImpl
import com.example.dogs_app.home.data.repository.DogRemoteDataSourceImpl
import com.example.dogs_app.home.data.repository.DogRepositoryImpl
import com.example.dogs_app.home.data.source.local.DogsLocalDataSource
import com.example.dogs_app.home.data.source.network.DogsRemoteDataSource
import com.example.dogs_app.home.domain.repository.DogRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class HomeRepositoryModule {

    @ViewModelScoped
    @Binds
    abstract fun bindLocalDataSource(
        localDataSourceImpl: DogLocalDataSourceImpl
    ): DogsLocalDataSource

    @ViewModelScoped
    @Binds
    abstract fun bindRemoteDataSource(
        remoteDataSourceImpl: DogRemoteDataSourceImpl
    ): DogsRemoteDataSource

    @ViewModelScoped
    @Binds
    abstract fun bindDogsRepository(
        dogRepositoryImpl: DogRepositoryImpl
    ): DogRepository
}