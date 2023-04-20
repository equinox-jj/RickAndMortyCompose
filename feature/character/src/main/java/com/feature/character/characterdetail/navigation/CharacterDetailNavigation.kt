package com.feature.character.characterdetail.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.feature.character.characterdetail.CharacterDetailScreen

const val characterDetailScreen = "character_detail_screen"

fun NavGraphBuilder.characterDetailScreen() {
    composable(route = characterDetailScreen) {
        CharacterDetailScreen()
    }
}