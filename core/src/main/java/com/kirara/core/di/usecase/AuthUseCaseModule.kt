package com.kirara.core.di.usecase

import com.kirara.core.domain.repositories.auth.AuthRepository
import com.kirara.core.domain.usecase.auth.LoginUseCase
import com.kirara.core.domain.usecase.auth.LogoutUseCase
import com.kirara.core.domain.usecase.auth.RefreshTokenUseCase
import com.kirara.core.domain.usecase.auth.RegisterUseCase
import com.kirara.core.util.SharedPreferencesHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object AuthUseCaseModule {

    @Provides
    fun provideRegisterUseCase(authRepository: AuthRepository, sharedPreferencesHelper: SharedPreferencesHelper) : RegisterUseCase {
        return RegisterUseCase(authRepository, sharedPreferencesHelper)
    }

    @Provides
    fun provideLoginUseCase(authRepository: AuthRepository, sharedPreferencesHelper: SharedPreferencesHelper) : LoginUseCase {
        return LoginUseCase(authRepository, sharedPreferencesHelper)
    }

    @Provides
    fun provideLogoutUseCase(authRepository: AuthRepository, sharedPreferencesHelper: SharedPreferencesHelper) : LogoutUseCase {
        return LogoutUseCase(authRepository, sharedPreferencesHelper)
    }

    @Provides
    fun provideRefreshTokenLogoutUseCase(authRepository: AuthRepository, sharedPreferencesHelper: SharedPreferencesHelper) : RefreshTokenUseCase {
        return RefreshTokenUseCase(authRepository, sharedPreferencesHelper)
    }

}