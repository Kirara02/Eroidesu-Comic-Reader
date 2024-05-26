package com.kirara.core.di

import com.kirara.core.data.datasource.remote.APIService
import com.kirara.core.data.repositories.auth.AuthRepositoryImpl
import com.kirara.core.data.repositories.manga.MangaRepositoryImpl
import com.kirara.core.data.repositories.user.UserRepositoryImpl
import com.kirara.core.domain.repositories.auth.AuthRepository
import com.kirara.core.domain.repositories.manga.MangaRepository
import com.kirara.core.domain.repositories.user.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMangaRepository(apiService: APIService) : MangaRepository {
        return MangaRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(apiService: APIService) : AuthRepository {
        return AuthRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideUserRepository(apiService: APIService) : UserRepository {
        return UserRepositoryImpl(apiService)
    }

}