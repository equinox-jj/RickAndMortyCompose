package com.feature.character.characterlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.feature.character.characterlist.component.CharacterCard
import com.feature.character.characterlist.component.RoundSearchBar

@ExperimentalMaterial3Api
@Composable
internal fun CharacterListScreen(
    modifier: Modifier = Modifier,
    characterListUiState: CharacterListUiState,
    searchQuery: String,
    onCardClicked: (Int) -> Unit,
    onSearchQueryChange: (String) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                modifier = modifier,
                title = { Text(text = "CharacterList") },
            )
        },
        content = {
            CharacterListContent(
                modifier = Modifier
                    .padding(paddingValues = it)
                    .padding(8.dp),
                characterListUiState = characterListUiState,
                searchQuery = searchQuery,
                onSearchQueryChange = onSearchQueryChange,
                onCardClicked = onCardClicked
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
    onSearchQueryChange: (String) -> Unit,
    onCardClicked: (Int) -> Unit,
) {
    Column(modifier = modifier) {
        RoundSearchBar(
            modifier = Modifier
                .padding(
                    top = 8.dp,
                    start = 16.dp,
                    end = 16.dp
                )
                .fillMaxWidth()
                .clip(ShapeDefaults.ExtraLarge),
            query = searchQuery,
            onQueryChange = { onSearchQueryChange(it) },
        )
        Spacer(modifier = Modifier.height(8.dp))
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
                        CharacterCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                                .padding(vertical = 8.dp),
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
                        .fillMaxSize(),
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