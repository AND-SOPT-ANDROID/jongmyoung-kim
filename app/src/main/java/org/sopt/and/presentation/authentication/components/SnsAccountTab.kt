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
import androidx.compose.ui.res.stringResource
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


/**
 * SNS account types
 *
 * 추후 패키지 분리 예정
 *
 * @property type
 * @property logo
 * @property logoColor
 * @constructor Create empty SNS account types
 */
enum class SNSAccountTypes(
    val type: String,
    @DrawableRes val logo: Int,
    val logoColor: Color
) {
    KAKAO("kakao", R.drawable.kakao_logo, Kakao),
    TWORLD("tworld", R.drawable.tworld_logo, TWorld),
    NAVER("naver", R.drawable.naver_logo, Naver),
    FACEBOOK("facebook", R.drawable.facebook_logo, Facebook),
    APPLE("apple", R.drawable.apple_logo, Color.White)
}

@Composable
fun SnsAccountTab(
    title: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.labelMedium,
    items: List<SNSAccountTypes> = SNSAccountTypes.entries
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
                text = title,
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
            items.forEach {
                SnsButton(
                    logo = it.logo,
                    backgroundColor = it.logoColor,
                    onSocialClick = {

                    }
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp),
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 4.dp),
                text = stringResource(R.string.sns_dot),
                color = DarkGray,
                style = MaterialTheme.typography.labelSmall
            )
            Text(
                text = stringResource(R.string.sns_notification),
                color = DarkGray,
                style = MaterialTheme.typography.labelSmall,
            )
        }
    }
}

@Composable
private fun SnsButton(
    @DrawableRes logo: Int,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
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
            contentDescription = stringResource(R.string.logo_sns)
        )
    }
}

@Preview
@Composable
private fun LogoButtonPreview() {
    ANDANDROIDTheme {
        SnsAccountTab(
            title = ""
        )
    }
}