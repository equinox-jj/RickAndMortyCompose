package com.feature.character.characterdetail.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.core.common.utils.Destinations
import com.feature.character.characterdetail.CharacterDetailScreen

fun NavGraphBuilder.characterDetailScreen() {
    composable(route = Destinations.CharacterDetailRoute.route) {
        CharacterDetailScreen()
    }
}