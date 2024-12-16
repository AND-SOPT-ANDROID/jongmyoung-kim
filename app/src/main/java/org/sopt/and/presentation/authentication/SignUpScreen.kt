package org.sopt.and.presentation.authentication

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import org.sopt.and.R
import org.sopt.and.presentation.authentication.SignUpContract.SignUpEvent
import org.sopt.and.presentation.authentication.SignUpContract.SignUpSideEffect
import org.sopt.and.presentation.authentication.components.AlertText
import org.sopt.and.presentation.authentication.components.AuthTextField
import org.sopt.and.presentation.authentication.components.SignUpTopBar
import org.sopt.and.presentation.authentication.components.SnsAccountTab
import org.sopt.and.presentation.common.CustomActionDialog
import org.sopt.and.presentation.extension.noRippleClickable
import org.sopt.and.presentation.theme.ANDANDROIDTheme
import org.sopt.and.presentation.theme.AndAndroidTheme


@Composable
fun SignUpScreen(
    onNavigateToSignIn: (String?) -> Unit,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = rememberUpdatedState(LocalLifecycleOwner.current).value
    val context = rememberUpdatedState(LocalContext.current).value
    val signUpMessage = stringResource(R.string.sign_up_success)

    BackHandler {
        viewModel.setEvent(SignUpEvent.OnDialogVisibilityChanged(false))
    }

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.sideEffect.collect { sideEffect ->
                when (sideEffect) {
                    is SignUpSideEffect.Toast ->
                        Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
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
            onDismissRequest = { viewModel.setEvent(SignUpEvent.OnDialogVisibilityChanged(false)) },
            onConfirmRequest = { viewModel.setEvent(SignUpEvent.OnCancelClicked) }
        )
    }

    SignUpScreenContent(
        emailInput = uiState.emailInput,
        isEmailValid = uiState.isEmailValid,
        onEmailChange = { viewModel.setEvent(SignUpEvent.OnEmailInputChanged(it)) },
        passwordInput = uiState.passwordInput,
        isPasswordValid = uiState.isPasswordValid,
        onPasswordChange = { viewModel.setEvent(SignUpEvent.OnPasswordInputChanged(it)) },
        hobbyInput = uiState.hobbyInput,
        isHobbyValid = uiState.isHobbyValid,
        onHobbyChange = { viewModel.setEvent(SignUpEvent.OnHobbyInputChanged(it)) },
        onCancelClick = { viewModel.setEvent(SignUpEvent.OnDialogVisibilityChanged(true)) },
        onSignUpClick = { viewModel.setEvent(SignUpEvent.OnSignUpClicked) },
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
    hobbyInput: String,
    isHobbyValid: Boolean,
    onHobbyChange: (String) -> Unit,
    onCancelClick: () -> Unit,
    onSignUpClick: () -> Unit,
    isButtonEnabled: Boolean,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val textFieldModifier = Modifier
        .fillMaxWidth()
        .height(48.dp)
        .clip(RoundedCornerShape(5.dp))
        .background(AndAndroidTheme.colors.gray300)
        .padding(horizontal = 15.dp)

    Scaffold(
        modifier = modifier.imePadding(),
        containerColor = AndAndroidTheme.colors.gray500,
        topBar = { SignUpTopBar(onCancelClick = onCancelClick) },
        bottomBar = {
            Box(
                modifier = Modifier
                    .height(48.dp)
                    .fillMaxWidth()
                    .background(if (isButtonEnabled) AndAndroidTheme.colors.wavveMain else AndAndroidTheme.colors.gray100)
                    .noRippleClickable(
                        enabled = isButtonEnabled,
                        onClick = onSignUpClick
                    )
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = stringResource(R.string.wavve_sign_up),
                    color = AndAndroidTheme.colors.white
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
                            style = SpanStyle(color = AndAndroidTheme.colors.white),
                            start = 0,
                            end = 9
                        )
                        addStyle(
                            style = SpanStyle(color = AndAndroidTheme.colors.gray100),
                            start = 9,
                            end = 12
                        )
                        addStyle(
                            style = SpanStyle(color = AndAndroidTheme.colors.white),
                            start = 13,
                            end = 24
                        )
                        addStyle(
                            style = SpanStyle(color = AndAndroidTheme.colors.gray100),
                            start = 25,
                            end = 29
                        )
                    },
                    style = MaterialTheme.typography.titleLarge
                )
                AuthTextField(
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .then(textFieldModifier),
                    value = emailInput,
                    hint = stringResource(R.string.sign_up_email_hint),
                    onValueChange = onEmailChange,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    cursorBrush = SolidColor(AndAndroidTheme.colors.white)
                )
                AlertText(
                    modifier = Modifier.padding(vertical = 10.dp),
                    value = stringResource(R.string.sign_up_email_noti),
                    textColor = if (isEmailValid || emailInput.isEmpty()) {
                        AndAndroidTheme.colors.gray100
                    } else AndAndroidTheme.colors.error
                )
                AuthTextField(
                    modifier = textFieldModifier,
                    value = passwordInput,
                    hint = stringResource(R.string.sign_up_password_hint),
                    onValueChange = onPasswordChange,
                    isPassword = true,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    cursorBrush = SolidColor(AndAndroidTheme.colors.white)
                )
                AlertText(
                    modifier = Modifier.padding(vertical = 10.dp),
                    value = stringResource(R.string.sign_up_password_noti),
                    textColor = if (isPasswordValid || passwordInput.isEmpty()) {
                        AndAndroidTheme.colors.gray100
                    } else AndAndroidTheme.colors.error
                )
                AuthTextField(
                    modifier = textFieldModifier,
                    value = hobbyInput,
                    hint = stringResource(R.string.sign_up_hobby_hint),
                    onValueChange = onHobbyChange,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { onSignUpClick() }
                    ),
                    cursorBrush = SolidColor(AndAndroidTheme.colors.white)
                )
                AlertText(
                    modifier = Modifier.padding(vertical = 10.dp),
                    value = stringResource(R.string.sign_up_hobby_noti),
                    textColor = if (isHobbyValid || hobbyInput.isEmpty()) {
                        AndAndroidTheme.colors.gray100
                    } else AndAndroidTheme.colors.error
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
