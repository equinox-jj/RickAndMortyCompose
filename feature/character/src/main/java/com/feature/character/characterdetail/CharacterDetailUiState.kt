package com.feature.character.characterdetail

import com.core.data.model.ResultsItem

sealed interface CharacterDetailUiState {
    object Loading : CharacterDetailUiState
    data class Success(val data: ResultsItem? = null) : CharacterDetailUiState
    data class Error(
        val errorMessage: String? = null,
        val errorCode: Int? = null
    ) : CharacterDetailUiState
}