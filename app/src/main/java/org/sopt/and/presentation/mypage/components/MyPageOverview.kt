package org.sopt.and.presentation.mypage.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.presentation.theme.ANDANDROIDTheme
import org.sopt.and.presentation.theme.AndAndroidTheme
import org.sopt.and.presentation.theme.Gray100
import org.sopt.and.presentation.theme.White


@Composable
fun MyPageOverview(
    modifier: Modifier = Modifier
) {
    // TODO: 추가 기능 구현 시 수정
    val overviews = listOf(
        Pair("전체 시청내역", "시청내역이 없어요."),
        Pair("관심 프로그램", "관심 프로그램이 없어요."),
        Pair("관심 영화", "관심 영화가 없어요."),
        Pair("관심 에디터Pick", "관심 에디터Pick이 없어요.")
    )

    Column(
        modifier = modifier
    ) {
        overviews.forEach {
            MyPageContentsItem(
                title = it.first,
                description = it.second
            )
        }
    }
}

@Composable
private fun MyPageContentsItem(
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    // TODO: Local database 추가 시 수정 예정
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            modifier = Modifier.padding(top = 12.dp),
            text = title,
            color = AndAndroidTheme.colors.white,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight(800)
        )
        Icon(
            modifier = Modifier
                .padding(top = 36.dp)
                .size(84.dp)
                .align(Alignment.CenterHorizontally),
            imageVector = ImageVector.vectorResource(R.drawable.ic_alert),
            contentDescription = stringResource(R.string.ic_alert),
            tint = AndAndroidTheme.colors.gray100
        )
        Text(
            modifier = Modifier
                .padding(top = 12.dp)
                .padding(bottom = 24.dp)
                .align(Alignment.CenterHorizontally),
            text = description,
            color = AndAndroidTheme.colors.gray100
        )
    }
}

@Preview
@Composable
private fun MyPageContentsPreview() {
    ANDANDROIDTheme {
        MyPageOverview()
    }
}
