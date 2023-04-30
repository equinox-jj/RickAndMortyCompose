package com.feature.character.characterlist.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@ExperimentalMaterial3Api
@Composable
internal fun CharacterCard(
    modifier: Modifier = Modifier,
    charId: Int = 0,
    charName: String = "",
    charGender: String = "",
    charImage: String = "",
    cardShape: Shape = ShapeDefaults.Medium,
    onCardClicked: (Int) -> Unit,
) {
    ElevatedCard(
        modifier = modifier,
        shape = cardShape,
        onClick = { onCardClicked(charId) },
    ) {
        Box {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = ImageRequest.Builder(LocalContext.current)
                    .crossfade(800)
                    .data(charImage)
                    .build(),
                contentScale = ContentScale.FillBounds,
                contentDescription = "Character Image",
            )
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
                        text = charName,
                        maxLines = 1,
                        style = TextStyle(
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                            color = Color.White
                        )
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = charGender,
                        maxLines = 1,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            color = Color.White
                        )
                    )
                }
            }
        }
    }
}