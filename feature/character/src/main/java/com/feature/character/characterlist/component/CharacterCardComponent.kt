package com.feature.character.characterlist.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@ExperimentalMaterial3Api
@Composable
internal fun CharacterCardComponent(
    modifier: Modifier = Modifier,
    charName: String = "",
    charGender: String = "",
    charImage: String = "",
    onCardClicked: () -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(16.dp),
        shape = ShapeDefaults.Medium,
        onClick = onCardClicked,
    ) {
        Box {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = charImage,
                contentDescription = "Character Image",
                contentScale = ContentScale.FillBounds,
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
                Column(modifier = modifier.padding(horizontal = 8.dp)) {
                    Text(
                        text = charName
                    )
                    Text(
                        text = charGender
                    )
                }
            }
        }
    }
}