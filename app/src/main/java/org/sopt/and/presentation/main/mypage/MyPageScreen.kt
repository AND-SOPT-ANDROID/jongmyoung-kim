package org.sopt.and.presentation.main.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.presentation.extension.noRippleClickable
import org.sopt.and.presentation.main.mypage.components.MyPageContents
import org.sopt.and.presentation.main.mypage.components.MyPageService
import org.sopt.and.presentation.main.mypage.components.MyPageTicket
import org.sopt.and.presentation.theme.ANDANDROIDTheme
import org.sopt.and.presentation.theme.Background
import org.sopt.and.presentation.theme.ExtraDarkGray
import org.sopt.and.presentation.theme.WavveMain

@Composable
fun MyPageScreen(
    userEmail: String = ""
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(ExtraDarkGray)
            .padding(top = 20.dp)
            .verticalScroll(scrollState)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(WavveMain)
            )
            Text(
                modifier = Modifier.padding(start = 15.dp),
                text = "${userEmail}님",
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                modifier = Modifier
                    .size(28.dp)
                    .noRippleClickable { },
                imageVector = ImageVector.vectorResource(R.drawable.ic_notification),
                contentDescription = null,
                tint = Color.White
            )
            Icon(
                modifier = Modifier
                    .padding(start = 24.dp)
                    .size(28.dp)
                    .noRippleClickable { },
                imageVector = ImageVector.vectorResource(R.drawable.ic_setting),
                contentDescription = null,
                tint = Color.White
            )
        }
        MyPageTicket(
            modifier = Modifier.padding(top = 8.dp),
            description = stringResource(R.string.first_purchase)
        )
        HorizontalDivider(
            thickness = 1.dp,
            color = Background
        )
        MyPageTicket(
            description = stringResource(R.string.no_ticket)
        )
        Column(
            modifier = Modifier
                .background(Background)
                .padding(horizontal = 12.dp)
        ) {
            MyPageContents()
            MyPageService(modifier = Modifier.padding(bottom = 48.dp))
        }
    }
}

@Preview
@Composable
private fun MyPageScreenPreview() {
    ANDANDROIDTheme {
        MyPageScreen()
    }
}