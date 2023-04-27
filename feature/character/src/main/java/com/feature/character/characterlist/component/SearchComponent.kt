package com.feature.character.characterlist.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@ExperimentalMaterial3Api
@Composable
internal fun SearchComponent(
    modifier: Modifier = Modifier,
    searchQuery: String,
    isSearchActive: Boolean,
    onSearch: (String) -> Unit,
    onQueryChange: (String) -> Unit,
    onActiveChange: (Boolean) -> Unit,
    leadingIcon: @Composable () -> Unit,
) {
    SearchBar(
        modifier = modifier,
        query = searchQuery,
        active = isSearchActive,
        onActiveChange = onActiveChange,
        onQueryChange = onQueryChange,
        onSearch = onSearch,
        leadingIcon = leadingIcon,
        content = {},
    )
}