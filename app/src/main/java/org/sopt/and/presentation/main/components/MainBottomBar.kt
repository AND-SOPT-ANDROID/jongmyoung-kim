package org.sopt.and.presentation.main.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.sopt.and.R
import org.sopt.and.presentation.main.MainBottomTab
import org.sopt.and.presentation.theme.ANDANDROIDTheme
import org.sopt.and.presentation.theme.AndAndroidTheme


@Composable
fun MainBottomBar(
    tabs: List<MainBottomTab>,
    onTabSelected: (MainBottomTab) -> Unit,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    isFirstSubscriber: Boolean = true // server driven value for user who's first subscriber
) {
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination
    val currentTab = MainBottomTab.entries.find {
        currentDestination?.route == it.route::class.qualifiedName
    }
    AnimatedVisibility(currentTab in MainBottomTab.entries) {
        Column {
            if (currentTab == MainBottomTab.HOME && isFirstSubscriber) { // only for user who's first subscriber
                GuaranteeBanner(modifier = Modifier.height(48.dp))
            }
            Row(
                modifier = modifier
                    .background(color = AndAndroidTheme.colors.gray600)
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .height(42.dp)
            ) {
                tabs.forEach { tab ->
                    MainBottomBarItem(
                        tab = tab,
                        selected = tab == currentTab,
                        onItemClick = { onTabSelected(tab) }
                    )
                }
            }
        }
    }
}

@Composable
private fun RowScope.MainBottomBarItem(
    tab: MainBottomTab,
    selected: Boolean,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val itemColor = if (selected) AndAndroidTheme.colors.white else AndAndroidTheme.colors.gray100
    val isMyPage = tab == MainBottomTab.MY_PAGE

    Column(
        modifier = modifier
            .weight(1f)
            .fillMaxHeight()
            .selectable(
                selected = selected,
                role = Role.Tab,
                onClick = onItemClick,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            modifier = Modifier.size(24.dp).let {
                if (isMyPage) it.clip(CircleShape) else it
            },
            painter = painterResource(tab.iconResId),
            contentDescription = stringResource(tab.contentDescription),
            tint = if (isMyPage) Color.Unspecified else itemColor,
        )
        Text(
            text = stringResource(tab.contentDescription),
            color = itemColor,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Composable
private fun GuaranteeBanner(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .clip(shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .background(
                brush = Brush.horizontalGradient(
                    listOf(AndAndroidTheme.colors.gradientBlue, AndAndroidTheme.colors.gradientTeal)
                )
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .padding(end = 8.dp)
                .size(24.dp),
            painter = painterResource(R.drawable.ic_ticket),
            contentDescription = stringResource(R.string.ic_ticket),
            tint = Color.Unspecified
        )
        Text(
            text = stringResource(R.string.home_first_purchase_guarantee),
            color = AndAndroidTheme.colors.white
        )
    }
}

@Preview
@Composable
private fun MainBottomBarPreview() {
    ANDANDROIDTheme {
        MainBottomBar(
            tabs = MainBottomTab.entries,
            onTabSelected = { },
            navController = rememberNavController()
        )
    }
}
