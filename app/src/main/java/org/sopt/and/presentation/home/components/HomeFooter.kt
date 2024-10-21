package org.sopt.and.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.presentation.theme.ANDANDROIDTheme
import org.sopt.and.presentation.theme.LightGray
import org.sopt.and.presentation.theme.White

@Composable
fun HomeFooter(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Icon(
                modifier = Modifier.size(38.dp),
                painter = painterResource(R.drawable.ic_footer_facebook),
                contentDescription = stringResource(R.string.ic_footer_facebook),
                tint = Color.Unspecified
            )
            Icon(
                modifier = Modifier.size(38.dp),
                painter = painterResource(R.drawable.ic_footer_naver),
                contentDescription = stringResource(R.string.ic_footer_naver),
                tint = Color.Unspecified
            )
        }
        Row(
            modifier = Modifier.height(54.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(color = LightGray)
                    ) {
                        append(stringResource(R.string.footer_agreement_service))
                        append(stringResource(R.string.vertical_bar))
                    }
                    withStyle(
                        style = SpanStyle(color = White, fontWeight = FontWeight.Bold)
                    ) {
                        append(stringResource(R.string.footer_agreement_privacy))
                    }
                    withStyle(
                        style = SpanStyle(color = LightGray)
                    ) {
                        append(stringResource(R.string.vertical_bar))
                        append(stringResource(R.string.footer_customer_faq))
                        append(stringResource(R.string.vertical_bar))
                        append(stringResource(R.string.footer_event_list))
                    }
                },
                style = MaterialTheme.typography.labelSmall,
                textAlign = TextAlign.Center
            )
        }
        Text(
            text = stringResource(R.string.footer_corp),
            color = LightGray,
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            text = stringResource(R.string.footer_address),
            color = LightGray,
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            text = stringResource(R.string.footer_business_registration),
            color = LightGray,
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            text = stringResource(R.string.footer_mail_order_registration),
            color = LightGray,
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            text = stringResource(R.string.footer_mail_order_information),
            color = LightGray,
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            text = stringResource(R.string.footer_service_provider),
            color = LightGray,
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            text = stringResource(R.string.footer_customer_service_center),
            color = LightGray,
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            text = stringResource(R.string.footer_email_address),
            color = LightGray,
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            text = stringResource(R.string.footer_copyright),
            color = LightGray,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Preview
@Composable
private fun HomeFooterContentPreview() {
    ANDANDROIDTheme {
        HomeFooter()
    }
}
