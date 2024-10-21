package org.sopt.and.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.sopt.and.R
import org.sopt.and.domain.model.Banner
import org.sopt.and.presentation.theme.ANDANDROIDTheme
import org.sopt.and.presentation.theme.ExtraDarkGray
import org.sopt.and.presentation.theme.LightGray
import org.sopt.and.presentation.theme.NavContainer
import org.sopt.and.presentation.theme.White

@Composable
fun HomeSwipePager(
    banners: List<Banner>,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(
        initialPage = 5,
        pageCount = { Int.MAX_VALUE }
    )

    LaunchedEffect(true) {
        while (true) {
            delay(3000)
            coroutineScope.launch {
                pagerState.animateScrollToPage(pagerState.currentPage + 1)
            }
        }
    }

    Box(
        modifier = modifier
    ) {
        HorizontalPager(
            state = pagerState,
            beyondViewportPageCount = 1,
            contentPadding = PaddingValues(horizontal = 12.dp),
            pageSpacing = 8.dp
        ) { page ->
            Box(
                modifier = modifier
                    .clip(RoundedCornerShape(8.dp))
                    .border(width = 1.dp, color = ExtraDarkGray, shape = RoundedCornerShape(8.dp))
            ) {
                AsyncImage(
                    model = banners[page % banners.size].bannerUri,
                    contentDescription = stringResource(R.string.img_banner)
                )
                AsyncImage(
                    model = banners[page % banners.size].subtitleUri,
                    contentDescription = stringResource(R.string.img_banner_description)
                )
            }
        }
        PageCountChip(
            modifier = Modifier
                .padding(end = 36.dp, bottom = 22.dp)
                .align(Alignment.BottomEnd),
            totalPage = banners.size,
            currentPage = pagerState.currentPage % banners.size
        )
    }
}

@Composable
private fun PageCountChip(
    totalPage: Int,
    currentPage: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(NavContainer)
            .padding(vertical = 3.dp, horizontal = 10.dp)
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(color = White)
                ) {
                    append("${currentPage + 1}")
                }
                withStyle(
                    style = SpanStyle(color = LightGray)
                ) {
                    append(stringResource(R.string.vertical_bar))
                    append("$totalPage")
                }
            }
        )
    }
}

@Preview
@Composable
private fun HomeSwipePagerPreview() {
    ANDANDROIDTheme {
        HomeSwipePager(banners = emptyList())
    }
}