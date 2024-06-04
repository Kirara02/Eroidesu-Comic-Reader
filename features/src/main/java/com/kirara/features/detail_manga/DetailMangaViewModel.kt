package com.kirara.features.detail_manga

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirara.core.data.UiState
import com.kirara.core.data.datasource.local.entity.MangaEntity
import com.kirara.core.data.model.mapper.MangaMapper
import com.kirara.core.data.model.response.BaseResponse
import com.kirara.core.data.model.response.Chapter
import com.kirara.core.data.model.response.Manga
import com.kirara.core.domain.usecase.manga.GetMangaByIdUseCase
import com.kirara.core.domain.usecase.manga.GetMangaListChaptersUseCase
import com.kirara.core.domain.usecase.manga.db.DeleteMangaDbUseCase
import com.kirara.core.domain.usecase.manga.db.GetMangaByIdDbUseCase
import com.kirara.core.domain.usecase.manga.db.InsertMangaDbUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class DetailMangaViewModel @Inject constructor(
    private val getMangaByIdUseCase: GetMangaByIdUseCase,
    private val getMangaListChaptersUseCase: GetMangaListChaptersUseCase,
    private val insertMangaDbUseCase: InsertMangaDbUseCase,
    private val deleteMangaDbUseCase: DeleteMangaDbUseCase,
    private val getMangaByIdDbUseCase: GetMangaByIdDbUseCase
) : ViewModel() {
    private val _uiStateManga: MutableStateFlow<UiState<BaseResponse<Manga>>> = MutableStateFlow(UiState.Loading)
    val uiStateManga: StateFlow<UiState<BaseResponse<Manga>>> = _uiStateManga

    private val _uiStateChapter: MutableStateFlow<UiState<BaseResponse<List<Chapter>>>> = MutableStateFlow(UiState.Loading)
    val uiStateChapter: StateFlow<UiState<BaseResponse<List<Chapter>>>> = _uiStateChapter

    private val _uiStateMangaEntity: MutableStateFlow<UiState<MangaEntity>> = MutableStateFlow(UiState.Loading)

    private val _hasSavedManga = MutableStateFlow(false)
    val hasSavedManga: StateFlow<Boolean> get() = _hasSavedManga

    fun getMangaByIdApiCall(id: Int){
        viewModelScope.launch {
            try {
                getMangaByIdUseCase.executeWithToken(id).catch {
                    _uiStateManga.value = UiState.Error(it.message.toString())
                }.collect { manga ->
                    getMangaByIdDbCall(manga.data.id ?: 0)
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

    private suspend fun getMangaByIdDbCall(id: Int) {
        withContext(Dispatchers.IO) {
            try {
                getMangaByIdDbUseCase.execute(id.toLong()).catch {
                    _uiStateMangaEntity.value = UiState.Error(it.message.toString())
                    Log.e("ERROR", "getMangaByIdDbCall: ${it.message}", )
                    _hasSavedManga.value = false
                }.collect {
                    Log.d("SUCCESS", "getMangaByIdDbCall: ${it.name}", )
                    _hasSavedManga.value = true
                }
            } catch (e: Exception) {
                _hasSavedManga.value = false
                Log.e("ERROR", "getMangaByIdDbCall: ${e.message}", )
                _uiStateMangaEntity.value = UiState.Error(e.message.toString())
            }
        }
    }


    fun insertMangaDb(manga: Manga){
        viewModelScope.launch {
            val longInsertStatus = insertMangaDbUseCase.execute(MangaMapper.mapFromProductToEntity(manga))
            if(longInsertStatus > 0){
                _hasSavedManga.value = true
                manga.id?.let { getMangaByIdDbCall(it) }
            }
        }
    }


    fun deleteMangaDb(manga: Manga){
        viewModelScope.launch {
            val intDeleteStatus = deleteMangaDbUseCase.execute(MangaMapper.mapFromProductToEntity(manga))
            if(intDeleteStatus > 0){
                _hasSavedManga.value = false
                manga.id?.let { getMangaByIdDbCall(it) }
            }
        }
    }
}