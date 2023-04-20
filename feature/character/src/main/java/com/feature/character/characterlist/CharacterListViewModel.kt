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
    private val characterRepository: CharacterRepository,
) : ViewModel() {

    val charState: StateFlow<CharacterUiState> = characterRepository
        .getCharacterList()
        .distinctUntilChanged()
        .map { result ->
            when (result) {
                is Results.Loading -> CharacterUiState.Loading
                is Results.Success -> CharacterUiState.Success(result.data?.results)
                is Results.Error -> CharacterUiState.Error(
                    errorMessage = result.errorMessage,
                    errorCode = result.errorCode,
                )
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = CharacterUiState.Loading
        )

}