package org.sopt.and.presentation.mypage.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch
import org.sopt.and.presentation.theme.ANDANDROIDTheme
import org.sopt.and.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPageSettingBottomSheet(
    onDismissRequest: () -> Unit,
    onButtonClicked: () -> Unit,
    hobbyInput: String,
    onHobbyInputChange: (String) -> Unit,
    passwordInput: String,
    onPasswordInputChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        modifier = modifier.fillMaxSize()
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = hobbyInput,
            onValueChange = onHobbyInputChange
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = passwordInput,
            onValueChange = onPasswordInputChange
        )
        Button(
            onClick = {
                coroutineScope.launch { sheetState.hide() }.invokeOnCompletion {
                    onButtonClicked()
                }
            }
        ) {
            Text(stringResource(R.string.change))
        }
    }
}

@Preview
@Composable
private fun MyPageSettingBottomSheetPreview() {
    ANDANDROIDTheme {
        MyPageSettingBottomSheet(
            onDismissRequest = {},
            onButtonClicked = {},
            hobbyInput = "",
            onHobbyInputChange = {},
            passwordInput = "",
            onPasswordInputChange = {}
        )
    }
}