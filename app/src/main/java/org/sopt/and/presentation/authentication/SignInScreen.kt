package org.sopt.and.presentation.authentication

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.presentation.authentication.components.CustomTextField
import org.sopt.and.presentation.authentication.components.SignInTopBar
import org.sopt.and.presentation.authentication.components.SnsAccountTab
import org.sopt.and.presentation.extension.noRippleClickable
import org.sopt.and.presentation.theme.ANDANDROIDTheme
import org.sopt.and.presentation.theme.LightGray
import org.sopt.and.presentation.theme.WavveMain
import org.sopt.and.presentation.util.Constants

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    onSignUpClick: () -> Unit = {},
    onSignInClick: (String, String) -> Unit
) {
    var emailInput by remember { mutableStateOf("") }
    var passwordInput by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SignInTopBar()

        CustomTextField(
            modifier = Modifier
                .padding(top = 48.dp)
                .height(56.dp),
            value = emailInput,
            hint = stringResource(R.string.sign_in_email_hint),
            onValueChange = {
                if (it.length <= Constants.MAX_EMAIL) emailInput = it
            },
            shape = RoundedCornerShape(5.dp)
        )
//        AlertText(
//            value = stringResource(R.string.sign_in_email_noti),
//            textColor = ErrorText
//        )
        CustomTextField(
            modifier = Modifier
                .padding(top = 4.dp)
                .height(56.dp),
            value = passwordInput,
            hint = stringResource(R.string.sign_in_password_hint),
            onValueChange = {
                if (it.length <= Constants.MAX_PASSWORD) passwordInput = it
            },
            isPassword = true,
            shape = RoundedCornerShape(5.dp),
            visualTransformation = PasswordVisualTransformation()
        )
//        AlertText(
//            value = stringResource(R.string.sign_in_password_noti),
//            textColor = ErrorText
//        )
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
            onClick = { onSignInClick(emailInput, passwordInput) },
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
                modifier = Modifier.noRippleClickable {
                    onSignUpClick()
                },
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
        SignInScreen(Modifier, {}, { _, _ -> })
    }
}