package org.sopt.and.presentation.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.sopt.and.R
import org.sopt.and.presentation.common.CustomTabRow
import org.sopt.and.presentation.search.components.SearchPopularItem
import org.sopt.and.presentation.search.components.SearchTagButton
import org.sopt.and.presentation.search.components.SearchTextField
import org.sopt.and.presentation.theme.ANDANDROIDTheme
import org.sopt.and.presentation.theme.AndAndroidTheme


@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SearchScreenContent(
        popularSeries = uiState.popularSeriesPosters,
        popularMovies = uiState.popularMoviePosters,
        text = uiState.searchInput,
        onTextChange = { viewModel.updateSearchInput(it) },
        onTabClick = { viewModel.onTabClicked(it) }
    )
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalLayoutApi::class)
@Composable
private fun SearchScreenContent(
    popularSeries: List<Pair<String, String>>,
    popularMovies: List<Pair<String, String>>,
    text: String,
    onTextChange: (String) -> Unit,
    onTabClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    var tab by remember { mutableStateOf(0) }
    val commonModifier = Modifier.padding(horizontal = 12.dp)
    val focusManager = LocalFocusManager.current
    val lazyListState = rememberLazyListState()

    LaunchedEffect(lazyListState) {
        snapshotFlow {
            lazyListState.isScrollInProgress
        }.collect { isScrolling ->
            if (isScrolling) {
                focusManager.clearFocus()
            }
        }
    }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        SearchTextField(
            value = text,
            onValueChange = onTextChange,
            modifier = commonModifier.padding(bottom = 8.dp)
        )
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            state = lazyListState
        ) {
            item {
                Row(
                    modifier = commonModifier.padding(vertical = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    SearchTagButton(
                        iconResId = R.drawable.ic_series,
                        titleResId = R.string.tag_button_series,
                        descriptionResId = R.string.ic_series,
                        modifier = Modifier.weight(1f)
                    )
                    SearchTagButton(
                        iconResId = R.drawable.ic_movie,
                        titleResId = R.string.tag_button_movie,
                        descriptionResId = R.string.ic_movie,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            stickyHeader {
                CustomTabRow(
                    tabTitles = listOf("인기 시리즈", "인기 영화"),
                    selectedTabIndex = tab,
                    onTabSelected = {
                        tab = it
                    }
                )
            }
            // Popular Series, Movies를 HorizontalPager로 묶어야 함
            item {
                popularSeries.forEach {
                    Column {
                        SearchPopularItem(
                            title = it.first,
                            imageUri = it.second,
                            modifier = commonModifier
                        )
                        HorizontalDivider(
                            thickness = 1.dp,
                            color = AndAndroidTheme.colors.gray300
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun SearchScreenPreview() {
    ANDANDROIDTheme {
        SearchScreen()
    }
}
