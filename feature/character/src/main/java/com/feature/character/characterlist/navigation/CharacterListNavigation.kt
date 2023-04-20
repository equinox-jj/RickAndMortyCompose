package com.feature.character.characterlist.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.core.common.utils.NavRoutes
import com.feature.character.characterlist.CharacterListScreen
import com.feature.character.characterlist.CharacterListViewModel

const val characterListScreen = NavRoutes.characterListRoute

@ExperimentalMaterial3Api
fun NavGraphBuilder.characterListScreen() {
    composable(route = characterListScreen) {
        val viewModel: CharacterListViewModel = hiltViewModel()
        val characterUiState by viewModel.charState.collectAsStateWithLifecycle()

        CharacterListScreen(characterUiState = characterUiState)
    }
}