package org.sopt.and.presentation.authentication.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.presentation.theme.ANDANDROIDTheme
import org.sopt.and.presentation.theme.ErrorText
import org.sopt.and.presentation.theme.LightGray

/**
 * Alert text
 *
 * 입력한 아이디, 비밀번호에 따라 빨간색으로 표시되는 안내 문구
 *
 * @param modifier
 * @param value
 */
@Composable
fun AlertText(
    value: String,
    modifier: Modifier = Modifier,
    textColor: Color = LightGray
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            modifier = Modifier.padding(end = 4.dp),
            imageVector = ImageVector.vectorResource(R.drawable.ic_alert),
            contentDescription = null,
            tint = textColor
        )
        Text(
            modifier = Modifier.padding(top = 2.dp),
            text = value,
            color = textColor,
            style = MaterialTheme.typography.labelMedium,
        )
    }
}

@Preview
@Composable
private fun AlertTextPreview() {
    ANDANDROIDTheme {
        AlertText(
            value = stringResource(R.string.sign_up_email_noti),
        )
    }
}