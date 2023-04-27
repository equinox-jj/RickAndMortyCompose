package com.feature.character.characterlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.feature.character.characterlist.component.CharacterCardComponent
import com.feature.character.characterlist.component.SearchComponent

@ExperimentalMaterial3Api
@Composable
internal fun CharacterListScreen(
    modifier: Modifier = Modifier,
    characterListUiState: CharacterListUiState,
    searchQuery: String,
    isSearchActive: Boolean,
    onQueryChange: (String) -> Unit,
    onSearchActiveChange: (Boolean) -> Unit,
    onCardClicked: (Int) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        content = {
            CharacterListContent(
                modifier = Modifier.padding(paddingValues = it),
                characterListUiState = characterListUiState,
                onCardClicked = onCardClicked,
                searchQuery = searchQuery,
                isSearchActive = isSearchActive,
                onQueryChange = onQueryChange,
                onSearchActiveChange = onSearchActiveChange,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                    )
                },
            )
        },
    )
}

@ExperimentalMaterial3Api
@Composable
internal fun CharacterListContent(
    modifier: Modifier = Modifier,
    characterListUiState: CharacterListUiState,
    searchQuery: String,
    isSearchActive: Boolean,
    onQueryChange: (String) -> Unit,
    onSearchActiveChange: (Boolean) -> Unit,
    onCardClicked: (Int) -> Unit,
    leadingIcon: @Composable () -> Unit,
) {
    Column(modifier = modifier) {
        SearchComponent(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            searchQuery = searchQuery,
            isSearchActive = isSearchActive,
            onSearch = onQueryChange,
            onQueryChange = onQueryChange,
            onActiveChange = onSearchActiveChange,
            leadingIcon = leadingIcon,
        )

        when (characterListUiState) {
            is CharacterListUiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }

            is CharacterListUiState.Success -> {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(count = characterListUiState.data?.size ?: 0) { index ->
                        val data = characterListUiState.data?.get(index)
                        CharacterCardComponent(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                                .padding(8.dp),
                            charId = data?.id ?: 0,
                            charName = data?.name.orEmpty(),
                            charGender = data?.gender.orEmpty(),
                            charImage = data?.image.orEmpty(),
                            onCardClicked = onCardClicked,
                        )
                    }
                }
            }

            is CharacterListUiState.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 8.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Column {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = characterListUiState.errorCode.toString(),
                            style = TextStyle(
                                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                                fontWeight = FontWeight.ExtraBold
                            ),
                            textAlign = TextAlign.Center,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = characterListUiState.errorMessage.toString(),
                            style = TextStyle(
                                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                fontWeight = FontWeight.Bold
                            ),
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }
    }
}