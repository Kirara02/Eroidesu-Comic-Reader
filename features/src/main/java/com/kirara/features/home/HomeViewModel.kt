package com.kirara.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirara.core.data.UiState
import com.kirara.core.data.model.response.MangaResponse
import com.kirara.core.domain.usecase.manga.GetMangasUseCase
import com.kirara.core.domain.usecase.manga.GetPopularMangasUseCase
import com.kirara.core.util.SharedPreferencesHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMangasUseCase: GetMangasUseCase,
    private val getPopularMangasUseCase: GetPopularMangasUseCase,
    private val sharedPreferencesHelper: SharedPreferencesHelper
) : ViewModel(){
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    private val _uiStateManga: MutableStateFlow<UiState<MangaResponse>> = MutableStateFlow(UiState.Loading)
    val uiStateManga: StateFlow<UiState<MangaResponse>> = _uiStateManga

    private val _uiStatePopularManga: MutableStateFlow<UiState<MangaResponse>> = MutableStateFlow(UiState.Loading)
    val uiStatePopularManga: StateFlow<UiState<MangaResponse>> = _uiStatePopularManga

    private val token: String = "Bearer ${sharedPreferencesHelper.getAccessToken()}"

    private val _name = MutableStateFlow<String>("")
    val name: StateFlow<String> get() = _name

    init {
        _name.value = sharedPreferencesHelper.getName() ?: "Guest"
        refresh()
    }

    fun getMangasApiCall(){
        viewModelScope.launch {
            try {
                getMangasUseCase.executeWithToken(Unit)
                    .catch {
                        _uiStateManga.value = UiState.Error(it.message.toString())
                    }
                    .collect { manga ->
                        _uiStateManga.value = UiState.Success(manga)
                    }
            } catch (e: Exception) {
                _uiStateManga.value = UiState.Error(e.message.toString())
            }
        }
    }

    fun getPopularMangasApiCall(){
        viewModelScope.launch {
            try {
                getPopularMangasUseCase.executeWithToken(Unit)
                    .catch {
                        _uiStatePopularManga.value = UiState.Error(it.message.toString())
                    }
                    .collect { manga ->
                        _uiStatePopularManga.value = UiState.Success(manga)
                    }
            } catch (e: Exception) {
                _uiStatePopularManga.value = UiState.Error(e.message.toString())
            }
        }
    }

    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.emit(false)
            _uiStateManga.value = UiState.Loading
            _uiStatePopularManga.value = UiState.Loading
            getMangasApiCall()
            getPopularMangasApiCall()
        }
    }
}