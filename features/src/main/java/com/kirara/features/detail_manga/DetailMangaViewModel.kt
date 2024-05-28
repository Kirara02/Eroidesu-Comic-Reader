package com.kirara.features.detail_manga

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirara.core.data.UiState
import com.kirara.core.data.model.request.GetMangaByIdRequest
import com.kirara.core.data.model.response.BaseResponse
import com.kirara.core.data.model.response.Chapter
import com.kirara.core.data.model.response.Manga
import com.kirara.core.domain.usecase.manga.GetMangaByIdUseCase
import com.kirara.core.domain.usecase.manga.GetMangaByNameUseCase
import com.kirara.core.domain.usecase.manga.GetMangaListChaptersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailMangaViewModel @Inject constructor(
    private val getMangaByIdUseCase: GetMangaByIdUseCase,
    private val getMangaByNameUseCase: GetMangaByNameUseCase,
    private val getMangaListChaptersUseCase: GetMangaListChaptersUseCase
) : ViewModel() {
    private val _uiStateManga: MutableStateFlow<UiState<BaseResponse<Manga>>> = MutableStateFlow(UiState.Loading)
    val uiStateManga: StateFlow<UiState<BaseResponse<Manga>>> = _uiStateManga

    private val _uiStateChapter: MutableStateFlow<UiState<BaseResponse<List<Chapter>>>> = MutableStateFlow(UiState.Loading)
    val uiStateChapter: StateFlow<UiState<BaseResponse<List<Chapter>>>> = _uiStateChapter


    fun getMangaByIdApiCall(id: Int){
        viewModelScope.launch {
            try {
                getMangaByIdUseCase.executeWithToken(id).catch {
                    _uiStateManga.value = UiState.Error(it.message.toString())
                }.collect { manga ->
                    _uiStateManga.value = UiState.Success(manga)
                }
            }catch (e: Exception){
                _uiStateManga.value = UiState.Error(e.message.toString())
            }
        }
    }

    fun getMangaByNameApiCall(name: String){
        viewModelScope.launch {
            try {
                getMangaByNameUseCase.executeWithToken(name).catch {
                    _uiStateManga.value = UiState.Error(it.message.toString())
                }.collect { manga ->
                    _uiStateManga.value = UiState.Success(manga)
                }
            }catch (e: Exception){
                _uiStateManga.value = UiState.Error(e.message.toString())
            }
        }
    }

    fun getMangaListChaptersApiCall(id: Int){
        viewModelScope.launch {
            try {
                getMangaListChaptersUseCase.executeWithToken(id).catch {
                    _uiStateChapter.value = UiState.Error(it.message.toString())
                }.collect { manga ->
                    _uiStateChapter.value = UiState.Success(manga)
                }
            }catch (e: Exception){
                _uiStateChapter.value = UiState.Error(e.message.toString())
            }
        }
    }
}