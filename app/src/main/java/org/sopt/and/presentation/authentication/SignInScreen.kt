package org.sopt.and.presentation.authentication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import org.sopt.and.R
import org.sopt.and.presentation.authentication.SignInContract.SignInEvent
import org.sopt.and.presentation.authentication.SignInContract.SignInSideEffect
import org.sopt.and.presentation.authentication.components.AlertText
import org.sopt.and.presentation.authentication.components.AuthTextField
import org.sopt.and.presentation.authentication.components.SignInTopBar
import org.sopt.and.presentation.authentication.components.SnsAccountTab
import org.sopt.and.presentation.common.CustomConfirmDialog
import org.sopt.and.presentation.extension.noRippleClickable
import org.sopt.and.presentation.theme.ANDANDROIDTheme
import org.sopt.and.presentation.theme.AndAndroidTheme

@Composable
fun SignInScreen(
    onNavigateToHome: (String?) -> Unit,
    onNavigateToSignUp: () -> Unit,
    viewModel: SignInViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = rememberUpdatedState(LocalLifecycleOwner.current).value
    val signInMessage = stringResource(R.string.sign_in_success)

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.sideEffect.collect { sideEffect ->
                when (sideEffect) {
                    is SignInSideEffect.NavigateToHome -> onNavigateToHome(signInMessage)
                    is SignInSideEffect.NavigateToSignUp -> onNavigateToSignUp()
                }
            }
        }
    }

    if (uiState.isDialogShown) {
        CustomConfirmDialog(
            title = R.string.wavve,
            description = R.string.sign_in_failed,
            onDismissRequest = { viewModel.setEvent(SignInEvent.OnDialogVisibilityChanged(false)) },
            dismissText = R.string.confirm,
        )
    }

    SignInScreenContent(
        emailInput = uiState.emailInput,
        onEmailChange = { viewModel.setEvent(SignInEvent.OnEmailInputChanged(it)) },
        passwordInput = uiState.passwordInput,
        onPasswordChange = { viewModel.setEvent(SignInEvent.OnPasswordInputChanged(it)) },
        showEmailError = uiState.isEmailErrorShown,
        showPasswordError = uiState.isPasswordErrorShown,
        onNavigateToSignUp = { viewModel.setEvent(SignInEvent.OnNavigateToSignUp) },
        onSignInClick = { viewModel.setEvent(SignInEvent.OnSignInClicked) }
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
    val textFieldModifier = Modifier
        .height(52.dp)
        .fillMaxWidth()
        .clip(RoundedCornerShape(5.dp))
        .background(AndAndroidTheme.colors.gray400)
        .padding(horizontal = 16.dp)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SignInTopBar()
        AuthTextField(
            modifier = Modifier
                .padding(top = 48.dp)
                .then(textFieldModifier),
            value = emailInput,
            hint = stringResource(R.string.sign_in_email_hint),
            onValueChange = onEmailChange,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            )
        )
        if (showEmailError) {
            AlertText(
                value = stringResource(R.string.sign_in_email_noti),
                textColor = AndAndroidTheme.colors.error
            )
        }
        AuthTextField(
            modifier = Modifier
                .padding(top = 4.dp)
                .then(textFieldModifier),
            value = passwordInput,
            hint = stringResource(R.string.sign_in_password_hint),
            onValueChange = onPasswordChange,
            isPassword = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardActions = KeyboardActions(
                onDone = {
                    onSignInClick()
                    keyboardController?.hide()
                }
            )
        )
        if (showPasswordError) {
            AlertText(
                value = stringResource(R.string.sign_in_password_noti),
                textColor = AndAndroidTheme.colors.error
            )
        }
        Button(
            modifier = Modifier
                .padding(top = 32.dp)
                .height(48.dp),
            colors = ButtonColors(
                containerColor = AndAndroidTheme.colors.wavveMain,
                contentColor = AndAndroidTheme.colors.white,
                disabledContainerColor = AndAndroidTheme.colors.wavveMain,
                disabledContentColor = AndAndroidTheme.colors.white
            ),
            onClick = onSignInClick,
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
                color = AndAndroidTheme.colors.gray100,
                style = MaterialTheme.typography.labelSmall
            )
            Text(
                text = stringResource(R.string.vertical_bar),
                color = AndAndroidTheme.colors.gray100,
                style = MaterialTheme.typography.labelSmall
            )
            Text(
                text = stringResource(R.string.reset_password),
                color = AndAndroidTheme.colors.gray100,
                style = MaterialTheme.typography.labelSmall
            )
            Text(
                text = stringResource(R.string.vertical_bar),
                color = AndAndroidTheme.colors.gray100,
                style = MaterialTheme.typography.labelSmall
            )
            Text(
                modifier = Modifier.noRippleClickable(onNavigateToSignUp),
                text = stringResource(R.string.sign_up),
                color = AndAndroidTheme.colors.gray100,
                style = MaterialTheme.typography.labelSmall
            )
        }
        SnsAccountTab(
            modifier = Modifier.imePadding(),
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
