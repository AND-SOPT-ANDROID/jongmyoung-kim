package org.sopt.and.presentation.authentication

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import org.sopt.and.R
import org.sopt.and.presentation.authentication.components.AlertText
import org.sopt.and.presentation.authentication.components.AuthTextField
import org.sopt.and.presentation.authentication.components.SignInTopBar
import org.sopt.and.presentation.authentication.components.SnsAccountTab
import org.sopt.and.presentation.common.CustomConfirmDialog
import org.sopt.and.presentation.extension.noRippleClickable
import org.sopt.and.presentation.theme.ANDANDROIDTheme
import org.sopt.and.presentation.theme.Error
import org.sopt.and.presentation.theme.LightGray
import org.sopt.and.presentation.theme.WavveMain

@Composable
fun SignInScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToSignUp: () -> Unit,
    viewModel: SignInViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val lifecycleOwner = LocalLifecycleOwner.current

    var showEmailError by remember { mutableStateOf(false) }
    var showPasswordError by remember { mutableStateOf(false) }

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.sideEffect.collect { sideEffect ->
                when (sideEffect) {
                    is SignInSideEffect.NavigateToHome -> onNavigateToHome()
                    is SignInSideEffect.NavigateToSignUp -> onNavigateToSignUp()
                    is SignInSideEffect.InvalidEmail -> {
                        showEmailError = true
                        showPasswordError = false
                    }
                    is SignInSideEffect.InvalidPassword -> {
                        showPasswordError = true
                        showEmailError = false
                    }
                    is SignInSideEffect.SignInFailed -> {
                        showEmailError = false
                        showPasswordError = false
                    }
                }
            }
        }
    }

    if (uiState.isDialogShown) {
        CustomConfirmDialog(
            title = R.string.wavve,
            description = R.string.sign_in_failed,
            onDismissRequest = {
                viewModel.updateDialogVisibility(false)
            },
            dismissText = R.string.confirm,
        )
    }

    SignInScreenContent(
        emailInput = uiState.emailInput,
        onEmailChange = { viewModel.updateEmailInput(it) },
        passwordInput = uiState.passwordInput,
        onPasswordChange = { viewModel.updatePasswordInput(it) },
        showEmailError = showEmailError,
        showPasswordError = showPasswordError,
        onNavigateToSignUp = viewModel::navigateToSignUp,
        onSignInClick = viewModel::signIn
    )
}

@Composable
private fun SignInScreenContent(
    emailInput: String,
    onEmailChange: (String) -> Unit,
    passwordInput: String,
    onPasswordChange: (String) -> Unit,
    showEmailError: Boolean,
    showPasswordError: Boolean,
    onNavigateToSignUp: () -> Unit,
    onSignInClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SignInTopBar()
        AuthTextField(
            modifier = Modifier
                .padding(top = 48.dp)
                .height(56.dp),
            value = emailInput,
            hint = stringResource(R.string.sign_in_email_hint),
            onValueChange = { onEmailChange(it) },
            shape = RoundedCornerShape(5.dp)
        )
        if (showEmailError) {
            AlertText(
                value = stringResource(R.string.sign_in_email_noti),
                textColor = Error
            )
        }
        AuthTextField(
            modifier = Modifier
                .padding(top = 4.dp)
                .height(56.dp),
            value = passwordInput,
            hint = stringResource(R.string.sign_in_password_hint),
            onValueChange = { onPasswordChange(it) },
            isPassword = true,
            keyboardActions = KeyboardActions(
                onDone = {
                    onSignInClick()
                    keyboardController?.hide()
                }
            ),
            shape = RoundedCornerShape(5.dp),
            visualTransformation = PasswordVisualTransformation()
        )
        if (showPasswordError) {
            AlertText(
                value = stringResource(R.string.sign_in_password_noti),
                textColor = Error
            )
        }
        Button(
            modifier = Modifier
                .padding(top = 32.dp)
                .height(48.dp),
            colors = ButtonColors(
                containerColor = WavveMain,
                contentColor = Color.White,
                disabledContainerColor = WavveMain,
                disabledContentColor = Color.White
            ),
            onClick = { onSignInClick() },
            shape = RoundedCornerShape(100.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.sign_in),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
        }
        Row(
            modifier = Modifier.padding(top = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.find_id),
                color = LightGray,
                style = MaterialTheme.typography.labelSmall
            )
            Text(
                text = stringResource(R.string.vertical_bar),
                color = LightGray,
                style = MaterialTheme.typography.labelSmall
            )
            Text(
                text = stringResource(R.string.reset_password),
                color = LightGray,
                style = MaterialTheme.typography.labelSmall
            )
            Text(
                text = stringResource(R.string.vertical_bar),
                color = LightGray,
                style = MaterialTheme.typography.labelSmall
            )
            Text(
                modifier = Modifier.noRippleClickable { onNavigateToSignUp() },
                text = stringResource(R.string.sign_up),
                color = LightGray,
                style = MaterialTheme.typography.labelSmall
            )
        }
        SnsAccountTab(
            title = stringResource(R.string.sns_sign_in),
            textStyle = MaterialTheme.typography.labelLarge
        )
    }
}

@Preview
@Composable
private fun SignInScreenPreview() {
    ANDANDROIDTheme {
        SignInScreen(
            onNavigateToHome = {},
            onNavigateToSignUp = {}
        )
    }
}