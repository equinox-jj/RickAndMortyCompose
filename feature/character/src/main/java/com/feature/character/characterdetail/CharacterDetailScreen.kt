package com.feature.character.characterdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.core.common.components.ErrorContent

@Composable
internal fun CharacterDetailScreen(
    characterDetailUiState: CharacterDetailUiState,
    onIconBackPressed: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = {
            CharacterDetailContent(
                modifier = Modifier.padding(paddingValues = it),
                characterDetailUiState = characterDetailUiState,
                onIconBackPressed = { onIconBackPressed() }
            )
        },
    )
}

@Composable
internal fun CharacterDetailContent(
    modifier: Modifier = Modifier,
    characterDetailUiState: CharacterDetailUiState,
    onIconBackPressed: () -> Unit,
) {
    when (characterDetailUiState) {
        is CharacterDetailUiState.Loading -> {
            Box(modifier = modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

        is CharacterDetailUiState.Success -> {
            CharacterDetailHeader(
                modifier = modifier,
                characterDetailUiState = characterDetailUiState,
                onIconBackPressed = onIconBackPressed
            )
        }

        is CharacterDetailUiState.Error -> {
            ErrorContent(
                modifier = Modifier.fillMaxSize(),
                errorMessage = characterDetailUiState.errorMessage.orEmpty(),
                errorCode = characterDetailUiState.errorCode ?: 0
            )
        }
    }
}

@Composable
internal fun CharacterDetailHeader(
    modifier: Modifier,
    characterDetailUiState: CharacterDetailUiState.Success,
    onIconBackPressed: () -> Unit,
) {
    Column(modifier = modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(
                        shape = RoundedCornerShape(
                            bottomEnd = 20.dp,
                            bottomStart = 20.dp,
                        )
                    ),
                model = characterDetailUiState.data?.image,
                contentDescription = "Character Detail Image",
                contentScale = ContentScale.FillBounds
            )

            IconButton(
                modifier = Modifier
                    .padding(16.dp)
                    .size(24.dp)
                    .background(
                        color = Color.Gray,
                        shape = CircleShape,
                    )
                    .align(Alignment.TopStart),
                onClick = { onIconBackPressed() },
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back Icon Button"
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 250f,
                        ),
                        shape = RoundedCornerShape(
                            bottomEnd = 20.dp,
                            bottomStart = 20.dp,
                        )
                    ),
                contentAlignment = Alignment.BottomStart,
            ) {
                Column(
                    modifier = Modifier.padding(
                        start = 8.dp,
                        end = 8.dp,
                        bottom = 8.dp,
                    )
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = characterDetailUiState.data?.name.orEmpty(),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        style = TextStyle(
                            color = Color.White,
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            fontWeight = FontWeight.Medium,
                        )
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = characterDetailUiState.data?.gender.orEmpty(),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        style = TextStyle(
                            color = Color.White,
                            fontSize = MaterialTheme.typography.bodySmall.fontSize,
                            fontWeight = FontWeight.Medium,
                        )
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = characterDetailUiState.data?.status.orEmpty(),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        style = TextStyle(
                            color = Color.White,
                            fontSize = MaterialTheme.typography.bodySmall.fontSize,
                            fontWeight = FontWeight.Medium,
                        )
                    )
                }
            }
        }
    }
}