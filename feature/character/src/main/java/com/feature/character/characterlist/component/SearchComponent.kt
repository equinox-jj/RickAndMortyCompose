package com.feature.character.characterlist.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@ExperimentalMaterial3Api
@Composable
internal fun SearchComponent(
    modifier: Modifier = Modifier,
) {
    SearchBar(
        modifier = modifier,
        query = "",
        onQueryChange = {},
        onSearch = {},
        active = false,
        onActiveChange = {},
    ) {

    }
}