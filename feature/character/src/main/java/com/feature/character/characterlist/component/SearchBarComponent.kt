package com.feature.character.characterlist.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@ExperimentalMaterial3Api
@Composable
fun SearchBarComponent(
    modifier: Modifier = Modifier,
    query: String = "",
    onQueryChange: (String) -> Unit,
) {
    TextField(
        modifier = modifier,
        singleLine = true,
        value = query,
        onValueChange = { onQueryChange(it) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon"
            )
        },
    )
}