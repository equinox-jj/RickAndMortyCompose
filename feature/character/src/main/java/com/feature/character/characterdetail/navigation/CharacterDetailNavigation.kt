package com.feature.character.characterdetail.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.core.common.utils.Constants.CHAR_DETAIL_ARG
import com.core.common.utils.Destinations
import com.feature.character.characterdetail.CharacterDetailScreen
import com.feature.character.characterdetail.CharacterDetailViewModel

fun NavGraphBuilder.characterDetailScreen(
    navigateBack: () -> Unit,
) {
    composable(
        route = Destinations.CharacterDetailRoute.route,
        arguments = listOf(navArgument(name = CHAR_DETAIL_ARG) { type = NavType.IntType })
    ) {
        val viewModel: CharacterDetailViewModel = hiltViewModel()

        val characterUiState by viewModel.detailState.collectAsStateWithLifecycle()

        CharacterDetailScreen(
            characterDetailUiState = characterUiState,
            onIconBackPressed = { navigateBack() }
        )
    }
}