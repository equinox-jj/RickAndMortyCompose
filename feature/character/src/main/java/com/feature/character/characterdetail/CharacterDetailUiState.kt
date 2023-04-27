package com.feature.character.characterdetail

import com.core.data.model.ResultsItem

sealed interface CharacterDetailUiState {
    object Loading : CharacterDetailUiState
    data class Success(val data: ResultsItem) : CharacterDetailUiState
    data class Error(val errorMessage: String, val errorCode: Int)
}