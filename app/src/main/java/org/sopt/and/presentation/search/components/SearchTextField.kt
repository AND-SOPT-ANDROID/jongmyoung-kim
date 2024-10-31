package org.sopt.and.presentation.search.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.presentation.theme.ANDANDROIDTheme
import org.sopt.and.presentation.theme.LightGray
import org.sopt.and.presentation.theme.White

@Composable
fun SearchTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.CenterStart
    ) {
        Column {
            Row(
                modifier = Modifier.padding(vertical = 12.dp),
            ) {
                Icon(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(20.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.ic_search),
                    contentDescription = stringResource(R.string.ic_search),
                    tint = LightGray
                )
                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(
                        // TODO: 검색 기능 구현
                    ),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium.copy(
                        color = White
                    ),
                    cursorBrush = SolidColor(White),
                ) { innerTextField ->
                    if (value.isEmpty()) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(R.string.search_text_field_hint),
                            color = LightGray,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                platformStyle = PlatformTextStyle(
                                    includeFontPadding = false
                                ),
                            )
                        )
                    }
                    innerTextField()
                }
            }
            HorizontalDivider(thickness = 1.dp, color = White)
        }
    }
}

@Preview
@Composable
private fun SearchTextFieldPreview() {
    ANDANDROIDTheme {
        SearchTextField(
            value = "",
            onValueChange = {}
        )
    }
}