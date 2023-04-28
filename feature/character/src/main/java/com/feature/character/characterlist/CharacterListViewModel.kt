package com.feature.character.characterlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.result.Results
import com.core.data.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    characterRepository: CharacterRepository,
) : ViewModel() {

    val charState: StateFlow<CharacterListUiState> = characterRepository
        .getCharacterList()
        .distinctUntilChanged()
        .map { response ->
            when (response) {
                is Results.Loading -> CharacterListUiState.Loading
                is Results.Success -> CharacterListUiState.Success(response.data?.results)
                is Results.Error -> CharacterListUiState.Error(
                    errorMessage = response.errorMessage,
                    errorCode = response.errorCode,
                )
            }
        }
        .stateIn(
            scope = viewModelScope,
            initialValue = CharacterListUiState.Loading,
            started = SharingStarted.WhileSubscribed(5000L)
        )
}