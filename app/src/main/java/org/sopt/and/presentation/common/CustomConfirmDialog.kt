package org.sopt.and.presentation.common

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.presentation.extension.noRippleClickable
import org.sopt.and.presentation.theme.ANDANDROIDTheme
import org.sopt.and.presentation.theme.AndAndroidTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomConfirmDialog(
    @StringRes title: Int,
    @StringRes description: Int,
    @StringRes dismissText: Int,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    BasicAlertDialog(
        onDismissRequest = onDismissRequest
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp))
                .background(AndAndroidTheme.colors.white)
                .padding(vertical = 16.dp, horizontal = 24.dp)
        ) {
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = stringResource(title),
                color = AndAndroidTheme.colors.gray400,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                modifier = Modifier.padding(bottom = 16.dp),
                text = stringResource(description),
                color = AndAndroidTheme.colors.gray400,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 8.dp)
                    .noRippleClickable(onDismissRequest),
                text = stringResource(dismissText),
                color = AndAndroidTheme.colors.wavveMain,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

@Preview
@Composable
private fun CommonDialogPreview() {
    ANDANDROIDTheme {
        CustomConfirmDialog(
            title = R.string.wavve,
            description = R.string.sign_in_failed,
            onDismissRequest = {},
            dismissText = R.string.sign_up
        )
    }
}
