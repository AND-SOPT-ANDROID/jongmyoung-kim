package org.sopt.and.presentation.authentication.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.presentation.extension.noRippleClickable
import org.sopt.and.presentation.theme.ANDANDROIDTheme
import org.sopt.and.presentation.theme.AndAndroidTheme


@Composable
fun SignUpTopBar(
    onCancelClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .height(56.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.sign_up),
            color = AndAndroidTheme.colors.white
        )
        Icon(
            modifier = Modifier
                .size(36.dp)
                .align(Alignment.CenterEnd)
                .noRippleClickable(onCancelClick),
            imageVector = ImageVector.vectorResource(R.drawable.ic_close),
            contentDescription = stringResource(R.string.ic_close),
            tint = AndAndroidTheme.colors.white
        )
    }
}

@Composable
fun SignInTopBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .height(56.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier
                .size(36.dp)
                .align(Alignment.CenterStart)
                .noRippleClickable(onBackClick),
            imageVector = ImageVector.vectorResource(R.drawable.ic_back),
            contentDescription = stringResource(R.string.ic_back),
            tint = AndAndroidTheme.colors.white
        )
        Icon(
            modifier = Modifier.align(Alignment.Center),
            imageVector = ImageVector.vectorResource(R.drawable.logo_wavve),
            contentDescription = stringResource(R.string.logo_wavve),
            tint = AndAndroidTheme.colors.white
        )
    }
}


/**
 * Welcome top bar
 *
 * 메인 화면에서 사용될 top bar
 *
 * @param onNavigateToLogin 로그인 화면으로 이동
 */
@Composable
fun WelcomeTopBar(
    modifier: Modifier = Modifier,
    onNavigateToLogin: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .height(56.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.align(Alignment.CenterStart),
            imageVector = ImageVector.vectorResource(R.drawable.logo_wavve),
            contentDescription = stringResource(R.string.logo_wavve),
            tint = AndAndroidTheme.colors.white
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .noRippleClickable(onNavigateToLogin),
            text = stringResource(R.string.sign_in),
            color = AndAndroidTheme.colors.white
        )
    }
}

@Preview
@Composable
private fun CustomTopBarPreview() {
    ANDANDROIDTheme {
        Column {
            SignUpTopBar(onCancelClick = {})
            SignInTopBar()
            WelcomeTopBar()
        }
    }
}
