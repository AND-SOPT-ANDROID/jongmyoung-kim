package org.sopt.and.presentation.mypage

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import org.sopt.and.R
import org.sopt.and.presentation.extension.noRippleClickable
import org.sopt.and.presentation.mypage.components.MyPageOverview
import org.sopt.and.presentation.mypage.components.MyPageService
import org.sopt.and.presentation.mypage.components.MyPageTicket
import org.sopt.and.presentation.mypage.sideeffect.MyPageSideEffect
import org.sopt.and.presentation.theme.ANDANDROIDTheme
import org.sopt.and.presentation.theme.AndAndroidTheme


@Composable
fun MyPageScreen(
    onNavigateToSignIn: (String?) -> Unit,
    viewModel: MyPageViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val signOutMessage = stringResource(R.string.sign_out_success)

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.sideEffect.collect { sideEffect ->
                when (sideEffect) {
                    is MyPageSideEffect.Toast ->
                        Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
                    is MyPageSideEffect.NavigateToSignIn -> onNavigateToSignIn(signOutMessage)
                }
            }
        }
    }

    MyPageScreenContent(
        userHobby = uiState.userHobby,
        onClickSignOut = viewModel::signOut
    )
}

@Composable
private fun MyPageScreenContent(
    userHobby: String,
    onClickSignOut: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(AndAndroidTheme.colors.gray300)
            .padding(top = 20.dp)
            .verticalScroll(scrollState)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box( // TODO: Profile 이미지 관련 기능 구현 시 수정
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(AndAndroidTheme.colors.wavveMain)
            )
            Text(
                modifier = Modifier.padding(start = 15.dp),
                text = stringResource(R.string.hobby, userHobby),
                color = AndAndroidTheme.colors.white,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                modifier = Modifier
                    .size(28.dp)
                    .noRippleClickable({}),
                imageVector = ImageVector.vectorResource(R.drawable.ic_notification),
                contentDescription = stringResource(R.string.ic_notification),
                tint = AndAndroidTheme.colors.white
            )
            Icon(
                modifier = Modifier
                    .size(28.dp)
                    .noRippleClickable({}),
                imageVector = ImageVector.vectorResource(R.drawable.ic_setting),
                contentDescription = stringResource(R.string.ic_setting),
                tint = AndAndroidTheme.colors.white
            )
        }
        // TODO: 임시 로그아웃 버튼, 추후 수정 필요
        Text(
            modifier = Modifier
                .padding(start = 24.dp)
                .noRippleClickable(onClickSignOut),
            text = stringResource(R.string.sign_out),
            color = AndAndroidTheme.colors.white,
            style = MaterialTheme.typography.bodyLarge
        )
        MyPageTicket(
            modifier = Modifier.padding(top = 8.dp),
            description = stringResource(R.string.first_purchase)
        )
        HorizontalDivider(
            thickness = 1.dp,
            color = AndAndroidTheme.colors.gray500
        )
        MyPageTicket(
            description = stringResource(R.string.no_ticket)
        )
        Column(
            modifier = Modifier
                .background(AndAndroidTheme.colors.gray500)
                .padding(horizontal = 12.dp)
        ) {
            MyPageOverview()
            MyPageService(modifier = Modifier.padding(bottom = 48.dp))
        }
    }
}

@Preview
@Composable
private fun MyPageScreenPreview() {
    ANDANDROIDTheme {
        MyPageScreen(
            onNavigateToSignIn = {}
        )
    }
}
