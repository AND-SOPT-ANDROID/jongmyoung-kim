package org.sopt.and.presentation.main.mypage.components

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.presentation.theme.ANDANDROIDTheme
import org.sopt.and.presentation.theme.LightGray

/**
 * My page contents
 *
 * 전체 시청내역, 관심 프로그램 등 컨텐츠 관련 컴포넌트, 추가 기능 구현 시 contents list 수정 예정
 *
 * @param modifier
 */
@Composable
fun MyPageContents(
    modifier: Modifier = Modifier
) {
    val contents = listOf(
        Pair("전체 시청내역", "시청내역이 없어요."),
        Pair("관심 프로그램", "관심 프로그램이 없어요."),
        Pair("관심 영화", "관심 영화가 없어요."),
        Pair("관심 에디터Pick", "관심 에디터Pick이 없어요.")
    )

    Column(
        modifier = modifier
    ) {
        contents.forEach {
            MyPageContentsItem(
                title = it.first,
                description = it.second
            )
        }
    }
}

@Composable
private fun MyPageContentsItem(
    modifier: Modifier = Modifier,
    title: String,
    description: String
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            modifier = Modifier.padding(top = 12.dp),
            text = title,
            color = Color.White,
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
            contentDescription = null,
            tint = LightGray
        )
        Text(
            modifier = Modifier
                .padding(top = 12.dp)
                .padding(bottom = 24.dp)
                .align(Alignment.CenterHorizontally),
            text = description,
            color = LightGray
        )
    }
}

@Preview
@Composable
private fun MyPageContentsPreview() {
    ANDANDROIDTheme {
        MyPageContents()
    }
}
