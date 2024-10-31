package org.sopt.and.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.sopt.and.presentation.home.components.HomeFooter
import org.sopt.and.presentation.home.components.HomeSwipePager
import org.sopt.and.presentation.home.components.HomeSwipePoster
import org.sopt.and.presentation.home.components.HomeTabRow
import org.sopt.and.presentation.home.components.HomeTopBar
import org.sopt.and.presentation.theme.ANDANDROIDTheme

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreenContent(
        banners = uiState.banners,
        posters = uiState.posters,
        rankedPosters = uiState.rankedPosters
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HomeScreenContent(
    banners: List<Pair<String, String>>,
    posters: List<Pair<String, List<String>>>,
    rankedPosters: Pair<String, List<String>>,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            HomeTopBar(modifier = Modifier.padding(16.dp))
        }
        stickyHeader {
            HomeTabRow()
        }
        item {
            HomeSwipePager(
                banners = banners,
                modifier = Modifier.padding(bottom = 10.dp)
            )
        }
        itemsIndexed(
            items = posters,
            key = { index, _ -> index },
            contentType = { _, item -> item }) { _, poster ->
            HomeSwipePoster(
                poster = poster,
                modifier = Modifier.padding(vertical = 12.dp)
            )
        }
        item {
            HomeSwipePoster(
                poster = rankedPosters,
                isRanked = true
            )
        }
        item {
            HomeFooter(
                modifier = Modifier.padding(
                    start = 12.dp,
                    top = 30.dp,
                    end = 12.dp
                )
            )
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    ANDANDROIDTheme {
        HomeScreen()
    }
}
