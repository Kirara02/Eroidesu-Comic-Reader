package com.kirara.core.di.usecase

import com.kirara.core.domain.repositories.auth.AuthRepository
import com.kirara.core.domain.usecase.auth.LoginUseCase
import com.kirara.core.domain.usecase.auth.LogoutUseCase
import com.kirara.core.domain.usecase.auth.RefreshTokenUseCase
import com.kirara.core.domain.usecase.auth.RegisterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object AuthUseCaseModule {

    @Provides
    fun provideRegisterUseCase(authRepository: AuthRepository) : RegisterUseCase {
        return RegisterUseCase(authRepository)
    }

    @Provides
    fun provideLoginUseCase(authRepository: AuthRepository) : LoginUseCase {
        return LoginUseCase(authRepository)
    }

    @Provides
    fun provideLogoutUseCase(authRepository: AuthRepository) : LogoutUseCase {
        return LogoutUseCase(authRepository)
    }

    @Provides
    fun provideRefreshTokenLogoutUseCase(authRepository: AuthRepository) : RefreshTokenUseCase {
        return RefreshTokenUseCase(authRepository)
    }

}