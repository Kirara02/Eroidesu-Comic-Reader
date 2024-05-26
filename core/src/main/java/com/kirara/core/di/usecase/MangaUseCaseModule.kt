package com.kirara.core.di.usecase

import com.kirara.core.domain.repositories.manga.MangaRepository
import com.kirara.core.domain.usecase.manga.GetMangaByIdUseCase
import com.kirara.core.domain.usecase.manga.GetMangasUseCase
import com.kirara.core.domain.usecase.manga.GetPopularMangasUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object MangaUseCaseModule {

    @Provides
    fun provideGetMangasUseCase(mangaRepository: MangaRepository) : GetMangasUseCase {
        return GetMangasUseCase(mangaRepository)
    }

    @Provides
    fun provideGetPopularMangaUseCase(mangaRepository: MangaRepository) : GetPopularMangasUseCase {
        return GetPopularMangasUseCase(mangaRepository)
    }

    @Provides
    fun provideGetMangaByIdUseCase(mangaRepository: MangaRepository) : GetMangaByIdUseCase {
        return GetMangaByIdUseCase(mangaRepository)
    }
}