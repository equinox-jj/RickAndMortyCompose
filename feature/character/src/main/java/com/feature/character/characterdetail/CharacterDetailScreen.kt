package com.feature.character.characterdetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage

@Composable
internal fun CharacterDetailScreen(
    modifier: Modifier = Modifier,
    characterDetailUiState: CharacterDetailUiState
) {
    Scaffold(
        modifier = modifier,
        content = {
            CharacterDetailContent(
                modifier = Modifier.padding(paddingValues = it),
                characterDetailUiState = characterDetailUiState,
            )
        },
    )
}

@Composable
internal fun CharacterDetailContent(
    modifier: Modifier = Modifier,
    characterDetailUiState: CharacterDetailUiState,
) {
    Column(modifier = modifier) {

        when (characterDetailUiState) {
            is CharacterDetailUiState.Loading -> {}
            is CharacterDetailUiState.Success -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = characterDetailUiState.data?.image,
                        contentDescription = "Character Image"
                    )
                }
            }

            is CharacterDetailUiState.Error -> {}
        }
    }
}