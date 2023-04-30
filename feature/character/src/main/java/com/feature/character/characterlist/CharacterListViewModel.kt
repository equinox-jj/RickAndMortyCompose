package com.feature.character.characterlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.result.Results
import com.core.data.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    characterRepository: CharacterRepository,
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val searchState = searchQuery
        .debounce(1000L)
        .flatMapLatest { charName ->
            characterRepository.getCharacterByName(name = charName)
                .flowOn(Dispatchers.IO)
                .map { response ->
                    when (response) {
                        is Results.Loading -> {
                            CharacterListUiState.Loading
                        }

                        is Results.Success -> {
                            CharacterListUiState.Success(data = response.data?.results)
                        }

                        is Results.Error -> {
                            CharacterListUiState.Error(
                                errorMessage = response.errorMessage,
                                errorCode = response.errorCode
                            )
                        }
                    }
                }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = CharacterListUiState.Loading
        )

    fun searchCharacter(query: String) {
        _searchQuery.value = query
    }
}