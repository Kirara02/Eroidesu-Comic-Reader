package com.kirara.features.bookmark

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirara.core.data.UiState
import com.kirara.core.data.datasource.local.entity.MangaEntity
import com.kirara.core.data.model.response.BaseResponse
import com.kirara.core.data.model.response.Manga
import com.kirara.core.domain.usecase.manga.db.GetMangaDbUseCase
import com.kirara.core.network.error.handleAppError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val getMangaDbUseCase: GetMangaDbUseCase
) : ViewModel() {
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    private val _uiState: MutableStateFlow<UiState<MutableList<MangaEntity>>> = MutableStateFlow(
        UiState.Loading)
    val uiState: StateFlow<UiState<MutableList<MangaEntity>>> = _uiState


    fun getMangaDbCall(){
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO){
                    getMangaDbUseCase.execute(Unit).catch {
                        _uiState.value = UiState.Error(it.message.toString())
                        Log.e("ERROR", "getMangaDbCall: ${it.message}")
                    }.collect{
                        _uiState.value = UiState.Success(it)
                    }
                }
            }   catch (e: Exception){
                _uiState.value = UiState.Error(e.message.toString())
                Log.e("ERROR", "getMangaDbCall: ${e.message}")
            }
        }
    }

    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.value = false
            _uiState.value = UiState.Loading
            getMangaDbCall()
        }
    }
}