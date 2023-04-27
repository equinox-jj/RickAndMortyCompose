package com.feature.character.characterlist.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.core.common.utils.Destinations
import com.feature.character.characterlist.CharacterListScreen
import com.feature.character.characterlist.CharacterListViewModel

@ExperimentalMaterial3Api
fun NavGraphBuilder.characterListScreen(
    navigateToDetail: (Int) -> Unit,
) {
    composable(route = Destinations.CharacterListRoute.route) {
        val viewModel: CharacterListViewModel = hiltViewModel()

        val characterUiState by viewModel.charState.collectAsStateWithLifecycle()
        val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
        val isSearchActive by viewModel.isSearchActive.collectAsStateWithLifecycle()

//        var query by rememberSaveable() {
//            mutableStateOf("")
//        }

//        var active by rememberSaveable() {
//            mutableStateOf(false)
//        }

        CharacterListScreen(
            characterListUiState = characterUiState,
            onCardClicked = navigateToDetail,
            searchQuery = searchQuery,
            isSearchActive = isSearchActive,
            onQueryChange = viewModel::onQueryChange,
            onSearchActiveChange = { isActive ->
                isActive
            },
        )
    }
}