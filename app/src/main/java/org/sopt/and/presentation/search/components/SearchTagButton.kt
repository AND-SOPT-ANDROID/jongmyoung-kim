package org.sopt.and.presentation.search.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.presentation.extension.noRippleClickable
import org.sopt.and.presentation.theme.ANDANDROIDTheme
import org.sopt.and.presentation.theme.AndAndroidTheme


@Composable
fun SearchTagButton(
    @DrawableRes iconResId: Int,
    @StringRes titleResId: Int,
    @StringRes descriptionResId: Int,
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit = {} // TODO: 각 태그 클릭 시, 대응하는 화면 표시
) {
    Row(
        modifier = modifier
            .height(48.dp)
            .border(
                width = 1.dp,
                color = AndAndroidTheme.colors.gray100,
                shape = RoundedCornerShape(24.dp),
            )
            .padding(vertical = 13.dp, horizontal = 16.dp)
            .noRippleClickable(onButtonClick)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(iconResId),
            contentDescription = stringResource(descriptionResId),
            tint = AndAndroidTheme.colors.white
        )
        Text(
            modifier = Modifier
                .padding(start = 8.dp)
                .weight(1f),
            text = stringResource(titleResId),
            color = AndAndroidTheme.colors.white,
            style = TextStyle(
                platformStyle = PlatformTextStyle(
                    includeFontPadding = false,
                ),
            )
        )
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_forward),
            contentDescription = stringResource(R.string.ic_forward),
            tint = AndAndroidTheme.colors.white
        )
    }
}

@Preview
@Composable
private fun SearchTagButtonPreview() {
    ANDANDROIDTheme {
        SearchTagButton(
            iconResId = R.drawable.ic_series,
            titleResId = R.string.tag_button_series,
            descriptionResId = R.string.ic_series
        )
    }
}
