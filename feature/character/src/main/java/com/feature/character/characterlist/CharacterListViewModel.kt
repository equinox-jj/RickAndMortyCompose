package com.feature.character.characterlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.result.Results
import com.core.data.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val characterRepository: CharacterRepository,
) : ViewModel() {

    val charState: StateFlow<CharacterListUiState> = characterRepository
        .getCharacterList()
        .distinctUntilChanged()
        .map { result ->
            when (result) {
                is Results.Loading -> CharacterListUiState.Loading
                is Results.Success -> CharacterListUiState.Success(result.data?.results)
                is Results.Error -> CharacterListUiState.Error(
                    errorMessage = result.errorMessage,
                    errorCode = result.errorCode,
                )
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = CharacterListUiState.Loading
        )

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _isSearchActive = MutableStateFlow(false)
    val isSearchActive = _isSearchActive.asStateFlow()

    private val _state = MutableStateFlow(CharacterListUiState.Loading)
    val state = searchQuery
        .debounce(1000L)
        .onEach { _isSearchActive.update { true } }
        .combine(characterRepository.getCharacterList()) { query, state ->
            if (query.isBlank()) {
                state
            } else {
                delay(2000L)
                when (state) {
                    is Results.Loading -> CharacterListUiState.Loading
                    is Results.Success -> CharacterListUiState.Success(data = state.data?.results)
                    is Results.Error -> CharacterListUiState.Error(
                        errorMessage = state.errorMessage,
                        errorCode = state.errorCode,
                    )
                }
            }
        }
        .onEach { _isSearchActive.update { false } }
        .stateIn(
            scope = viewModelScope,
            initialValue = CharacterListUiState.Loading,
            started = SharingStarted.WhileSubscribed(5000L)
        )

    fun onQueryChange(query: String) {
        _searchQuery.value = query
    }
}