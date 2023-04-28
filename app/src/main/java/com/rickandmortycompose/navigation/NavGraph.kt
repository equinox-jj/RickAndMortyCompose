package com.rickandmortycompose.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.core.common.utils.Destinations
import com.feature.character.characterdetail.navigation.characterDetailScreen
import com.feature.character.characterlist.navigation.characterListScreen

@ExperimentalMaterial3Api
@Composable
fun SetupNavGraph(
    startDestination: String,
    navHostController: NavHostController,
) {
    NavHost(
        startDestination = startDestination,
        navController = navHostController,
    ) {
        characterListScreen(
            navigateToDetail = {
                navHostController.navigate(Destinations.CharacterDetailRoute.passCharId(it))
            }
        )
        characterDetailScreen()
    }
}