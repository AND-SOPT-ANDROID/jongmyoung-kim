package org.sopt.and.presentation.home.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.presentation.theme.ANDANDROIDTheme
import org.sopt.and.presentation.theme.AndAndroidTheme


@Composable
fun HomeTopBar(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.logo_wavve),
            contentDescription = stringResource(R.string.logo_wavve),
            tint = AndAndroidTheme.colors.white
        )
        Spacer(Modifier.weight(1f))
        Icon(
            modifier = Modifier.padding(end = 12.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_cast),
            contentDescription = stringResource(R.string.ic_cast),
            tint = AndAndroidTheme.colors.white

        )
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_live),
            contentDescription = stringResource(R.string.ic_live),
            tint = AndAndroidTheme.colors.white
        )
    }
}

@Preview
@Composable
private fun HomeTopBarPreview() {
    ANDANDROIDTheme {
        HomeTopBar()
    }
}
