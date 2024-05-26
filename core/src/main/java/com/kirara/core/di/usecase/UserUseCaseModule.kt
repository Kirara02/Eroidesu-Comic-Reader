package com.kirara.core.di.usecase

import com.kirara.core.domain.repositories.user.UserRepository
import com.kirara.core.domain.usecase.user.GetUserUseCase
import com.kirara.core.util.SharedPreferencesHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UserUseCaseModule {
    @Provides
    fun provideGetUserUseCase(userRepository: UserRepository, sharedPreferencesHelper: SharedPreferencesHelper) : GetUserUseCase {
        return GetUserUseCase(userRepository, sharedPreferencesHelper)
    }
}