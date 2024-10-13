package org.sopt.and.presentation.main.mypage.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.presentation.extension.noRippleClickable
import org.sopt.and.presentation.theme.ANDANDROIDTheme
import org.sopt.and.presentation.theme.LightGray

@Composable
fun MyPageTicket(
    description: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(76.dp)
            .padding(horizontal = 12.dp)
            .padding(bottom = 8.dp)
            .noRippleClickable {

            },
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = description,
            color = LightGray,
            style = MaterialTheme.typography.bodyLarge
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.purchase),
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge
            )
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_forward),
                contentDescription = stringResource(R.string.purchase),
                tint = Color.White
            )
        }
    }
}

@Preview
@Composable
private fun MyPageTicketPreview() {
    ANDANDROIDTheme {
        MyPageTicket(
            description = "첫 결제 시 첫 달 100원!"
        )
    }
}