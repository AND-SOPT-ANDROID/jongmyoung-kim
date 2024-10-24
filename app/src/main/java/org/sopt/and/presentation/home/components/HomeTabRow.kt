package org.sopt.and.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.presentation.home.HomeTab
import org.sopt.and.presentation.theme.ANDANDROIDTheme
import org.sopt.and.presentation.theme.Background
import org.sopt.and.presentation.theme.White

@Composable
fun HomeTabRow(
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier
            .background(Background)
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        itemsIndexed(
            items = HomeTab.entries,
            key = { index, _ -> index },
            contentType = { _, item -> item.name }) { index, tab ->
            Text(
                modifier = Modifier
                    .padding(
                        start = 12.dp,
                        end = if (index == HomeTab.entries.lastIndex) 12.dp else 0.dp
                    ),
                text = stringResource(tab.title),
                color = White
            )
        }
    }
}

@Preview
@Composable
private fun CollapsingTopBarPreview() {
    ANDANDROIDTheme {
        HomeTabRow()
    }
}