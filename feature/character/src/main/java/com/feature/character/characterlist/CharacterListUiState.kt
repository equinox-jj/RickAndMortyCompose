package com.feature.character.characterlist

import com.core.data.model.ResultsItem

sealed interface CharacterListUiState {
    object Loading : CharacterListUiState
    data class Success(val data: List<ResultsItem>? = emptyList()) : CharacterListUiState
    data class Error(
        val errorMessage: String? = null,
        val errorCode: Int? = null,
    ) : CharacterListUiState
}