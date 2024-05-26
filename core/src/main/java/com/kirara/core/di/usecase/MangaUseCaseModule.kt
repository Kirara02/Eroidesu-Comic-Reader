package com.kirara.core.di.usecase

import com.kirara.core.domain.repositories.manga.MangaRepository
import com.kirara.core.domain.usecase.manga.GetMangaByIdUseCase
import com.kirara.core.domain.usecase.manga.GetMangasUseCase
import com.kirara.core.domain.usecase.manga.GetPopularMangasUseCase
import com.kirara.core.util.SharedPreferencesHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object MangaUseCaseModule {

    @Provides
    fun provideGetMangasUseCase(mangaRepository: MangaRepository, sharedPreferencesHelper: SharedPreferencesHelper) : GetMangasUseCase {
        return GetMangasUseCase(mangaRepository, sharedPreferencesHelper)
    }

    @Provides
    fun provideGetPopularMangaUseCase(mangaRepository: MangaRepository, sharedPreferencesHelper: SharedPreferencesHelper) : GetPopularMangasUseCase {
        return GetPopularMangasUseCase(mangaRepository, sharedPreferencesHelper)
    }

    @Provides
    fun provideGetMangaByIdUseCase(mangaRepository: MangaRepository, sharedPreferencesHelper: SharedPreferencesHelper) : GetMangaByIdUseCase {
        return GetMangaByIdUseCase(mangaRepository, sharedPreferencesHelper)
    }
}