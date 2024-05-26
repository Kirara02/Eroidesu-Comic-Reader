package com.kirara.features.detail_manga

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirara.core.data.UiState
import com.kirara.core.data.model.request.GetMangaByIdRequest
import com.kirara.core.data.model.response.BaseResponse
import com.kirara.core.data.model.response.Manga
import com.kirara.core.domain.usecase.manga.GetMangaByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailMangaViewModel @Inject constructor(
   private val getMangaByIdUseCase: GetMangaByIdUseCase
) : ViewModel() {
    private val _uiStateManga: MutableStateFlow<UiState<BaseResponse<Manga>>> = MutableStateFlow(UiState.Loading)
    val uiStateManga: StateFlow<UiState<BaseResponse<Manga>>> = _uiStateManga


    fun getMangaByIdApiCall(id: Int){
    val getManga: GetMangaByIdRequest = GetMangaByIdRequest(token = "", id = id)
        viewModelScope.launch {
            try {
                getMangaByIdUseCase.execute(getManga).catch {
                    _uiStateManga.value = UiState.Error(it.message.toString())
                }.collect { manga ->
                    _uiStateManga.value = UiState.Success(manga)
                }
            }catch (e: Exception){
                _uiStateManga.value = UiState.Error(e.message.toString())
            }
        }
    }
}