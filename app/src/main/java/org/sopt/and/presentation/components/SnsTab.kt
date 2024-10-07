package org.sopt.and.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.ui.theme.Facebook
import org.sopt.and.ui.theme.Kakao
import org.sopt.and.ui.theme.Naver
import org.sopt.and.ui.theme.TWorld


@Composable
fun SnsTab(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)

    ) {
        SnsButton(
            logo = R.drawable.kakao_logo,
            backgroundColor = Kakao,
            onSocialClick = {}
        )
        SnsButton(
            logo = R.drawable.tworld_logo,
            backgroundColor = TWorld,
            onSocialClick = {}
        )
        SnsButton(
            logo = R.drawable.naver_logo,
            backgroundColor = Naver,
            onSocialClick = {}
        )
        SnsButton(
            logo = R.drawable.facebook_logo,
            backgroundColor = Facebook,
            onSocialClick = {}
        )
        SnsButton(
            logo = R.drawable.apple_logo,
            backgroundColor = Color.White,
            onSocialClick = {}
        )
    }
}


@Composable
fun SnsButton(
    modifier: Modifier = Modifier,
    logo: Int,
    backgroundColor: Color,
    onSocialClick: () -> Unit
) {
    Box (
        modifier = modifier
            .clip(CircleShape)
            .background(backgroundColor)
            .size(44.dp)
            .clickable(onClick = onSocialClick),
        contentAlignment = Alignment.Center
    ){
        Image(
            modifier = Modifier
                .size(20.dp),
            painter = painterResource(logo),
            contentDescription = null
        )
    }
}


@Preview
@Composable
private fun LogoButtonPreview() {
    ANDANDROIDTheme {
        SnsTab()
    }
}