package com.feature.character.characterlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.feature.character.characterlist.component.CharacterCardComponent
import com.feature.character.characterlist.component.SearchComponent

@ExperimentalMaterial3Api
@Composable
internal fun CharacterListScreen(
    modifier: Modifier = Modifier,
    characterUiState: CharacterUiState,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        content = {
            Column(modifier = modifier.fillMaxSize()) {
                SearchComponent(modifier = modifier.align(Alignment.CenterHorizontally))
                Spacer(modifier = Modifier.height(8.dp))
                CharacterListContent(characterUiState = characterUiState)
            }
        },
    )
}

@ExperimentalMaterial3Api
@Composable
internal fun CharacterListContent(
    modifier: Modifier = Modifier,
    characterUiState: CharacterUiState,
) {
    when (characterUiState) {
        is CharacterUiState.Loading -> {

        }

        is CharacterUiState.Success -> {
            LazyColumn() {
                characterUiState.data?.size?.let {
                    items(it) { index ->
                        val data = characterUiState.data[index]
                        CharacterCardComponent(
                            charName = data.name ?: "",
                            charGender = data.gender ?: "",
                            charImage = data.image ?: "",
                            onCardClicked = {},
                        )
                    }
                }
            }
        }

        is CharacterUiState.Error -> {

        }
    }
}