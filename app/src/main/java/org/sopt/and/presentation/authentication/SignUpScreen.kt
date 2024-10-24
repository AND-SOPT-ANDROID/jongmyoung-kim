package org.sopt.and.presentation.authentication

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import org.sopt.and.R
import org.sopt.and.presentation.authentication.components.AlertText
import org.sopt.and.presentation.authentication.components.AuthTextField
import org.sopt.and.presentation.authentication.components.SignUpTopBar
import org.sopt.and.presentation.authentication.components.SnsAccountTab
import org.sopt.and.presentation.common.CustomActionDialog
import org.sopt.and.presentation.theme.ANDANDROIDTheme
import org.sopt.and.presentation.theme.Error
import org.sopt.and.presentation.theme.LightGray
import org.sopt.and.presentation.theme.WavveMain
import org.sopt.and.presentation.theme.White

@Composable
fun SignUpScreen(
    onNavigateToSignIn: (String?) -> Unit,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val signUpMessage = stringResource(R.string.sign_up_success)

    BackHandler {
        viewModel.updateDialogVisibility(true)
    }

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.sideEffect.collect { sideEffect ->
                when (sideEffect) {
                    is SignUpSideEffect.Toast -> {
                        Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
                    }
                    is SignUpSideEffect.NavigateToSignIn -> onNavigateToSignIn(signUpMessage)
                    is SignUpSideEffect.NavigateBackToSignIn -> onNavigateToSignIn(null)
                }
            }
        }
    }

    if (uiState.isDialogShown) {
        CustomActionDialog(
            title = R.string.wavve,
            description = R.string.sign_up_cancel_description,
            dismissText = R.string.dismiss,
            confirmText = R.string.confirm,
            onDismissRequest = { viewModel.updateDialogVisibility(false) },
            onConfirmRequest = viewModel::navigateToSignIn
        )
    }

    SignUpScreenContent(
        emailInput = uiState.emailInput,
        isEmailValid = uiState.isEmailValid,
        onEmailChange = { viewModel.updateEmailInput(it) },
        passwordInput = uiState.passwordInput,
        isPasswordValid = uiState.isPasswordValid,
        onPasswordChange = { viewModel.updatePasswordInput(it) },
        onCancelClick = { viewModel.updateDialogVisibility(true) },
        onSignUpClick = viewModel::signUp,
        isButtonEnabled = uiState.isButtonEnabled
    )
}

@Composable
private fun SignUpScreenContent(
    emailInput: String,
    isEmailValid: Boolean,
    onEmailChange: (String) -> Unit,
    passwordInput: String,
    isPasswordValid: Boolean,
    onPasswordChange: (String) -> Unit,
    onCancelClick: () -> Unit,
    onSignUpClick: () -> Unit,
    isButtonEnabled: Boolean,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = { SignUpTopBar(onCancelClick = onCancelClick) },
        bottomBar = {
            Box(
                modifier = Modifier
                    .height(48.dp)
                    .fillMaxWidth()
                    .background(if (isButtonEnabled) WavveMain else LightGray)
                    .clickable(
                        enabled = isButtonEnabled,
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() },
                    ) {
                        onSignUpClick()
                    }
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = stringResource(R.string.wavve_sign_up),
                    color = White
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = modifier.padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .verticalScroll(scrollState)
            ) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .padding(vertical = 12.dp),
                    text = buildAnnotatedString {
                        append(stringResource(R.string.sign_up_greeting))
                        addStyle(
                            style = SpanStyle(color = White),
                            start = 0,
                            end = 9
                        )
                        addStyle(
                            style = SpanStyle(color = LightGray),
                            start = 9,
                            end = 12
                        )
                        addStyle(
                            style = SpanStyle(color = White),
                            start = 13,
                            end = 24
                        )
                        addStyle(
                            style = SpanStyle(color = LightGray),
                            start = 25,
                            end = 29
                        )
                    },
                    style = MaterialTheme.typography.titleLarge
                )
                AuthTextField(
                    modifier = Modifier.padding(top = 4.dp),
                    value = emailInput,
                    hint = stringResource(R.string.sign_up_email_hint),
                    onValueChange = { onEmailChange(it) },
                    cursorBrush = SolidColor(White)
                )
                AlertText(
                    modifier = Modifier.padding(vertical = 10.dp),
                    value = stringResource(R.string.sign_up_email_noti),
                    textColor = if (isEmailValid || emailInput.isEmpty()) LightGray else Error
                )
                AuthTextField(
                    value = passwordInput,
                    hint = stringResource(R.string.sign_up_password_hint),
                    onValueChange = { onPasswordChange(it) },
                    isPassword = true,
                    visualTransformation = PasswordVisualTransformation(),
                    cursorBrush = SolidColor(White)
                )
                AlertText(
                    modifier = Modifier.padding(vertical = 10.dp),
                    value = stringResource(R.string.sign_up_password_noti),
                    textColor = if (isPasswordValid || passwordInput.isEmpty()) LightGray else Error
                )
                SnsAccountTab(
                    modifier = Modifier.padding(bottom = 96.dp),
                    title = stringResource(R.string.sns_sign_up)
                )
            }
        }
    }
}

@Preview
@Composable
private fun SignUpScreenPreview() {
    ANDANDROIDTheme {
        SignUpScreen(onNavigateToSignIn = {})
    }
}
