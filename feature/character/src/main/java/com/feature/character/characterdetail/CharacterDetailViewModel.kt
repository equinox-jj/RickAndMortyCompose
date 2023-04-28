package com.feature.character.characterdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.result.Results
import com.core.common.utils.Constants.CHAR_DETAIL_ARG
import com.core.data.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    characterRepository: CharacterRepository,
) : ViewModel() {

    private val charId = savedStateHandle.getStateFlow(CHAR_DETAIL_ARG, 0)
    val detailState: StateFlow<CharacterDetailUiState> = characterRepository
        .getSingleCharacter(charId.value)
        .distinctUntilChanged()
        .map { response ->
            when (response) {
                is Results.Loading -> CharacterDetailUiState.Loading
                is Results.Success -> CharacterDetailUiState.Success(data = response.data)
                is Results.Error -> CharacterDetailUiState.Error(
                    errorMessage = response.errorMessage,
                    errorCode = response.errorCode,
                )
            }
        }
        .stateIn(
            scope = viewModelScope,
            initialValue = CharacterDetailUiState.Loading,
            started = SharingStarted.WhileSubscribed(5000L)
        )
}