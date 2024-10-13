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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.presentation.extension.noRippleClickable
import org.sopt.and.presentation.theme.ANDANDROIDTheme

@Composable
fun SignUpTopBar(
    modifier: Modifier = Modifier,
    onCancelClick: () -> Unit = {}
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
            color = Color.White
        )
        Icon(
            modifier = Modifier
                .size(36.dp)
                .align(Alignment.CenterEnd)
                .noRippleClickable {
                    onCancelClick()
                    // TODO: 회원가입 취소 Dialog 표시
                },
            imageVector = ImageVector.vectorResource(R.drawable.ic_close),
            contentDescription = null,
            tint = Color.White
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
                .noRippleClickable {
                    onBackClick()
                },
            imageVector = ImageVector.vectorResource(R.drawable.ic_back),
            contentDescription = null,
            tint = Color.White
        )
        Icon(
            modifier = Modifier
                .align(Alignment.Center),
            imageVector = ImageVector.vectorResource(R.drawable.wavve_logo),
            contentDescription = null,
            tint = Color.White
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
            modifier = Modifier
                .align(Alignment.CenterStart),
            imageVector = ImageVector.vectorResource(R.drawable.wavve_logo),
            contentDescription = null,
            tint = Color.White
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .noRippleClickable {
                    onNavigateToLogin()
                },
            text = stringResource(R.string.sign_in),
            color = Color.White
        )
    }
}

@Preview
@Composable
private fun CustomTopBarPreview() {
    ANDANDROIDTheme {
        Column {
            SignUpTopBar()
            SignInTopBar()
            WelcomeTopBar()
        }
    }
}
