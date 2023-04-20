package com.core.data.di

import com.core.data.repository.CharacterRepository
import com.core.data.repository.CharacterRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindRepository(characterRepositoryImpl: CharacterRepositoryImpl): CharacterRepository
}