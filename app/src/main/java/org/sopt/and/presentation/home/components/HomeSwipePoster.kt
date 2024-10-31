package org.sopt.and.presentation.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.sopt.and.R
import org.sopt.and.presentation.theme.ANDANDROIDTheme
import org.sopt.and.presentation.theme.White

@Composable
fun HomeSwipePoster(
    poster: Pair<String, List<String>>,
    modifier: Modifier = Modifier,
    isRanked: Boolean = false
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = poster.first,
                color = White,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.ExtraBold
            )
            if (!isRanked) {
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_forward),
                    contentDescription = stringResource(R.string.ic_forward)
                )
            }
        }
        LazyRow(
            modifier = Modifier.padding(top = 8.dp)
        ) {
            itemsIndexed(
                items = poster.second,
                key = { index, _ -> index },
                contentType = { _, item -> item }
            ) { index, uri ->
                Box(
                    modifier = Modifier
                        .height(if (isRanked) 300.dp else 184.dp)
                        .padding(
                            start = if (index == 0) 12.dp else 8.dp,
                            end = if (index == poster.second.lastIndex) 12.dp else 0.dp,
                            bottom = if (isRanked) 24.dp else 0.dp
                        )
                ) {
                    AsyncImage(
                        model = uri,
                        contentDescription = poster.first,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                    if (isRanked) {
                        Text(
                            modifier = Modifier
                                .offset(x = 4.dp, y = 24.dp)
                                .align(Alignment.BottomStart),
                            text = "${index + 1}",
                            color = White,
                            style = MaterialTheme.typography.displayLarge,
                            fontWeight = FontWeight.ExtraBold,
                            fontStyle = FontStyle.Italic
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun HomeSwipePosterPreview() {
    ANDANDROIDTheme {
        HomeSwipePoster(
            Pair("", emptyList()),
        )
    }
}