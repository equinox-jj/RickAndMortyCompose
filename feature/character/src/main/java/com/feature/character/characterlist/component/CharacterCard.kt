package com.feature.character.characterlist.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.feature.character.R
import java.util.Locale

@ExperimentalMaterial3Api
@Composable
internal fun CharacterCard(
    modifier: Modifier = Modifier,
    charId: Int = 0,
    charName: String = "",
    charGender: String = "",
    charStatus: String = "",
    charImage: String = "",
    cardShape: Shape = ShapeDefaults.Medium,
    onCardClicked: (Int) -> Unit,
) {
    ElevatedCard(
        modifier = modifier
            .clip(cardShape)
            .clickable { onCardClicked(charId) }
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                modifier = Modifier
                    .size(150.dp)
                    .clip(cardShape),
                model = charImage,
                contentDescription = "Character Image",
                contentScale = ContentScale.FillBounds
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = charName,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        fontWeight = FontWeight.Bold
                    ),
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .background(
                                color = colorStatus(charStatus),
                                shape = CircleShape
                            )
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = charStatus.capitalize(Locale.ROOT),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        ),
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Image(
                        modifier = Modifier.size(10.dp),
                        painter = charGenderIcon(charGender),
                        contentDescription = "Gender Icon",
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = charGender.capitalize(Locale.ROOT),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        ),
                    )
                }
            }
        }
    }
}

@Composable
private fun charGenderIcon(charGender: String): Painter {
    return when {
        charGender.contains("Male") -> painterResource(id = R.drawable.male)
        charGender.contains("Female") -> painterResource(id = R.drawable.female)
        else -> painterResource(id = R.drawable.male)
    }
}

private fun colorStatus(charStatus: String): Color {
    return when {
        charStatus.contains("Alive") -> Color.Green
        charStatus.contains("Dead") -> Color.Red
        else -> Color.DarkGray
    }
}