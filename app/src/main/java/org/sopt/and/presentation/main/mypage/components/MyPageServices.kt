package org.sopt.and.presentation.main.mypage.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.presentation.theme.ANDANDROIDTheme
import org.sopt.and.presentation.theme.LightGray


/**
 * My page service
 *
 * submenu 관련 컴포넌트, 추가 기능 구현 시 service list 수정 예정
 *
 * @param modifier
 */
@Composable
fun MyPageService(
    modifier: Modifier = Modifier
) {
    val services = listOf(
        "구매 콘텐츠",
        "다운로드 콘텐츠",
        "이용권 구매",
        "코인/쿠폰",
        "웨이브온 이용권",
        "고객센터",
        "이벤트"
    )
    Column(
        modifier = modifier
    ) {
        services.forEach {
            MyPageServiceItem(title = it)
        }
    }
}

@Composable
fun MyPageServiceItem(
    title: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            color = LightGray
        )
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_forward),
            contentDescription = stringResource(R.string.ic_forward),
            tint = LightGray
        )
    }
}

@Preview
@Composable
private fun MyPageServicesPreview() {
    ANDANDROIDTheme {
        MyPageService()
    }
}