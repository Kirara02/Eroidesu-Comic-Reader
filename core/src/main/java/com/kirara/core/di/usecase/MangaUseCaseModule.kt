package com.kirara.core.di.usecase

import com.kirara.core.domain.repositories.manga.DbMangaRepository
import com.kirara.core.domain.repositories.manga.MangaRepository
import com.kirara.core.domain.usecase.manga.GetMangaByIdUseCase
import com.kirara.core.domain.usecase.manga.GetMangaByNameUseCase
import com.kirara.core.domain.usecase.manga.GetMangaChapterByIdUseCase
import com.kirara.core.domain.usecase.manga.GetMangaListChaptersUseCase
import com.kirara.core.domain.usecase.manga.GetMangasUseCase
import com.kirara.core.domain.usecase.manga.GetPopularMangasUseCase
import com.kirara.core.domain.usecase.manga.db.DeleteMangaDbUseCase
import com.kirara.core.domain.usecase.manga.db.GetMangaByIdDbUseCase
import com.kirara.core.domain.usecase.manga.db.GetMangaDbUseCase
import com.kirara.core.domain.usecase.manga.db.InsertMangaDbUseCase
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

    @Provides
    fun provideGetMangaByNameUseCase(mangaRepository: MangaRepository, sharedPreferencesHelper: SharedPreferencesHelper) : GetMangaByNameUseCase {
        return GetMangaByNameUseCase(mangaRepository, sharedPreferencesHelper)
    }

    @Provides
    fun provideGetMangaListChaptersUseCase(mangaRepository: MangaRepository, sharedPreferencesHelper: SharedPreferencesHelper) : GetMangaListChaptersUseCase {
        return GetMangaListChaptersUseCase(mangaRepository, sharedPreferencesHelper)
    }

    @Provides
    fun provideGetMangaChapterByIdUseCase(mangaRepository: MangaRepository, sharedPreferencesHelper: SharedPreferencesHelper) : GetMangaChapterByIdUseCase {
        return GetMangaChapterByIdUseCase(mangaRepository, sharedPreferencesHelper)
    }

    @Provides
    fun provideGetMangasDbUseCase(dbMangaRepository: DbMangaRepository, sharedPreferencesHelper: SharedPreferencesHelper) : GetMangaDbUseCase {
        return GetMangaDbUseCase(dbMangaRepository, sharedPreferencesHelper)
    }

    @Provides
    fun provideGetMangaByIdDbUseCase(dbMangaRepository: DbMangaRepository, sharedPreferencesHelper: SharedPreferencesHelper) : GetMangaByIdDbUseCase {
        return GetMangaByIdDbUseCase(dbMangaRepository, sharedPreferencesHelper)
    }

    @Provides
    fun provideInsertMangaDbUseCase(dbMangaRepository: DbMangaRepository, sharedPreferencesHelper: SharedPreferencesHelper) : InsertMangaDbUseCase {
        return InsertMangaDbUseCase(dbMangaRepository, sharedPreferencesHelper)
    }

    @Provides
    fun provideDeleteMangaDbUseCase(dbMangaRepository: DbMangaRepository, sharedPreferencesHelper: SharedPreferencesHelper) : DeleteMangaDbUseCase {
        return DeleteMangaDbUseCase(dbMangaRepository, sharedPreferencesHelper)
    }

}