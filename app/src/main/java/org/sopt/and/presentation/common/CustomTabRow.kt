package org.sopt.and.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.presentation.extension.noRippleClickable
import org.sopt.and.presentation.theme.ANDANDROIDTheme
import org.sopt.and.presentation.theme.Background
import org.sopt.and.presentation.theme.LightGray
import org.sopt.and.presentation.theme.WavveMain
import org.sopt.and.presentation.theme.White

@Composable
fun CustomTabRow(
    tabTitles: List<String>,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(Background)
            .padding(top = 12.dp)
            .height(40.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        tabTitles.forEachIndexed { index, tab ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .noRippleClickable(
                        onClick = { onTabSelected(index) }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = tab,
                        color = if (selectedTabIndex == index) White else LightGray,
                        style = MaterialTheme.typography.labelLarge
                    )
                    HorizontalDivider(
                        modifier = Modifier.width(70.dp),
                        thickness = 5.dp,
                        color = if (selectedTabIndex == index) WavveMain else Color.Transparent
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun CustomTabRowPreview() {
    ANDANDROIDTheme {
        Column {
            CustomTabRow(
                tabTitles = listOf("인기 시리즈", "인기 영화"),
                selectedTabIndex = 1,
                onTabSelected = {}
            )
        }
    }
}
