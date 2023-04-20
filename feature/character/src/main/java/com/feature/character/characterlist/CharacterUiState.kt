package com.feature.character.characterlist

import com.core.data.model.ResultsItem

sealed interface CharacterUiState {
    object Loading : CharacterUiState
    data class Success(val data: List<ResultsItem>? = emptyList()) : CharacterUiState
    data class Error(
        val errorMessage: String? = null,
        val errorCode: Int? = null,
    ) : CharacterUiState
}