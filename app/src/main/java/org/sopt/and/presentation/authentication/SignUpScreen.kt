package org.sopt.and.presentation.authentication

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.presentation.authentication.components.CustomTextField
import org.sopt.and.presentation.authentication.components.SnsAccountTab
import org.sopt.and.presentation.authentication.components.AlertText
import org.sopt.and.presentation.theme.ANDANDROIDTheme
import org.sopt.and.presentation.theme.ErrorText
import org.sopt.and.presentation.theme.LightGray
import org.sopt.and.presentation.theme.WavveMain
import org.sopt.and.presentation.util.coloredText

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    onSignUpClick: (String, String) -> Unit
) {
    val annotatedString = coloredText(
        string1 = "이메일과 비밀번호",
        stringColor1 = Color.White,
        string2 = "만으로\n",
        stringColor2 = LightGray
    )
    val annotatedString2 = coloredText(
        string1 = "Wavve를 즐길 수 ",
        stringColor1 = Color.White,
        string2 = "있어요!",
        stringColor2 = LightGray
    )
    val scrollState = rememberScrollState()
    var emailInput by remember { mutableStateOf("") }
    var passwordInput by remember { mutableStateOf("") }
    var isEmailValid by remember { mutableStateOf(Pair(true, "로그인, 비밀번호 찾기, 알림에 사용되니 정확한 이메일을 입력해 주세요.")) }
    var isPasswordValid by remember { mutableStateOf(true) }
    val emailFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }
    val buttonEnabled =
        isEmailValid.first && isPasswordValid && emailInput.isNotEmpty() && passwordInput.isNotEmpty()

    Box(
        modifier = modifier.fillMaxSize()
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
                text = annotatedString + annotatedString2,
                style = MaterialTheme.typography.titleLarge
            )
            CustomTextField(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .focusRequester(emailFocusRequester)
                    .onFocusChanged { focusState ->
                        if (!focusState.isFocused && emailInput.isNotEmpty()) {
                            isEmailValid = isValidEmail(emailInput)
                        }
                    },
                value = emailInput,
                hint = "wavve@example.com",
                onValueChange = {
                    emailInput = it
                }
            )
            AlertText(
                modifier = Modifier.padding(vertical = 10.dp),
                value = isEmailValid.second,
                textColor = if (isEmailValid.first) Color.White else ErrorText
            )
            CustomTextField(
                modifier = Modifier
                    .focusRequester(passwordFocusRequester)
                    .onFocusChanged { focusState ->
                        if (!focusState.isFocused && passwordInput.isNotEmpty()) {
                            isPasswordValid = isValidPassword(passwordInput)
                        }
                    },
                value = passwordInput,
                hint = "Wavve 비밀번호 설정",
                onValueChange = {
                    passwordInput = it
                },
                isPassword = true,
                visualTransformation = PasswordVisualTransformation()
            )
            AlertText(
                modifier = Modifier.padding(vertical = 10.dp),
                value = "비밀번호는 8~20자 이내로 영문 대소문자, 숫자, 특수문자 중 3가지 이상 혼용하여 입력해 주세요.",
                textColor = if (isPasswordValid) Color.White else ErrorText
            )
            SnsAccountTab(modifier = Modifier.padding(bottom = 96.dp))
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .height(48.dp)
                .fillMaxWidth()
                .background(
                    if (buttonEnabled) WavveMain else LightGray
                )
                .clickable(
                    enabled = buttonEnabled,
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    onSignUpClick(emailInput, passwordInput)
                }
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Wavve 회원가입",
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
private fun SignUpScreenPreview() {
    ANDANDROIDTheme {
        SignUpScreen(
            onSignUpClick = { _, _ -> }
        )
    }
}