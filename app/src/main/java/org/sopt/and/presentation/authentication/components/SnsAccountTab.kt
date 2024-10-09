package org.sopt.and.presentation.authentication.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.presentation.extension.noRippleClickable
import org.sopt.and.presentation.theme.ANDANDROIDTheme
import org.sopt.and.presentation.theme.DarkGray
import org.sopt.and.presentation.theme.ExtraDarkGray
import org.sopt.and.presentation.theme.Facebook
import org.sopt.and.presentation.theme.Kakao
import org.sopt.and.presentation.theme.LightGray
import org.sopt.and.presentation.theme.Naver
import org.sopt.and.presentation.theme.TWorld

@Composable
fun SnsAccountTab(
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.labelMedium
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(
                modifier = Modifier.weight(1f),
                color = ExtraDarkGray
            )
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = "또는 다른 서비스 계정으로 가입",
                color = LightGray,
                style = textStyle
            )
            HorizontalDivider(
                modifier = Modifier.weight(1f),
                color = ExtraDarkGray
            )
        }
        Row(
            modifier = Modifier.padding(top = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SnsButton(
                logo = R.drawable.kakao_logo,
                backgroundColor = Kakao,
                onSocialClick = {

                }
            )
            SnsButton(
                logo = R.drawable.tworld_logo,
                backgroundColor = TWorld,
                onSocialClick = {

                }
            )
            SnsButton(
                logo = R.drawable.naver_logo,
                backgroundColor = Naver,
                onSocialClick = {

                }
            )
            SnsButton(
                logo = R.drawable.facebook_logo,
                backgroundColor = Facebook,
                onSocialClick = {

                }
            )
            SnsButton(
                logo = R.drawable.apple_logo,
                backgroundColor = Color.White,
                onSocialClick = {

                }
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp),
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 4.dp),
                text = "·",
                color = DarkGray,
                style = MaterialTheme.typography.labelSmall
            )
            Text(
                text = "SNS계정으로 간편하게 가입하여 서비스를 이용하실 수 있습니다.\n기존 POOQ 계정 혹은 Wavve 계정과는 연동되지 않으니 이용이 참고하세요.",
                color = DarkGray,
                style = MaterialTheme.typography.labelSmall,
            )
        }
    }
}

@Composable
private fun SnsButton(
    modifier: Modifier = Modifier,
    @DrawableRes logo: Int,
    backgroundColor: Color,
    onSocialClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(backgroundColor)
            .size(44.dp)
            .noRippleClickable {
                onSocialClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .size(20.dp),
            imageVector = ImageVector.vectorResource(logo),
            contentDescription = null
        )
    }
}

@Preview
@Composable
private fun LogoButtonPreview() {
    ANDANDROIDTheme {
        SnsAccountTab()
    }
}