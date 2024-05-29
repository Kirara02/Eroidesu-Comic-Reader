package com.kirara.features.manga_chapter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirara.core.data.UiState
import com.kirara.core.data.model.response.BaseResponse
import com.kirara.core.data.model.response.ChapterImage
import com.kirara.core.domain.usecase.manga.GetMangaChapterByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MangaChapterViewModel @Inject constructor(
    private val getMangaChapterByIdUseCase: GetMangaChapterByIdUseCase
) : ViewModel() {
    private val _uiStateChapter: MutableStateFlow<UiState<BaseResponse<List<ChapterImage>>>> = MutableStateFlow(
        UiState.Loading)
    val uiStateChapter: StateFlow<UiState<BaseResponse<List<ChapterImage>>>> = _uiStateChapter

    fun getMangaChapterByIdApiCall(id: Int) {
        viewModelScope.launch {
            try {
                getMangaChapterByIdUseCase.executeWithToken(id).catch {
                    _uiStateChapter.value = UiState.Error(it.message.toString())
                }.collect { chapter ->
                    _uiStateChapter.value = UiState.Success(chapter)
                }
            }catch (e: Exception){
                _uiStateChapter.value = UiState.Error(e.message.toString())
            }
        }
    }

}