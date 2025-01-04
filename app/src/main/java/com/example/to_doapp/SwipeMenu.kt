package com.example.to_doapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun SwipeMenu(navController: NavController, content: @Composable () -> Unit) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Column(modifier = Modifier
                .padding(16.dp)
                .background(Color.White)
                .width(Dp(0.5f * LocalConfiguration.current.screenWidthDp))
                .fillMaxHeight(),
                horizontalAlignment = Alignment.Start
            ) {
                MenuItem(
                    text = "To-Dos",
                    iconResId = R.drawable.baseline_check_circle_24,
                    isSelected = currentRoute == "todo_list"
                ) {
                    navController.navigate("todo_list")
                }
                MenuItem(
                    text = "Calendar",
                    iconResId = R.drawable.baseline_calendar_today_24,
                    isSelected = currentRoute == "calendar"
                ) {
                    navController.navigate("calendar")
                }
                MenuItem(
                    text = "Settings",
                    iconResId = R.drawable.baseline_settings_24,
                    isSelected = currentRoute == "settings"
                ) {
                    navController.navigate("settings")
                }
            }
        },
        content = {
            content()
        }
    )
}

@Composable
fun MenuItem(text: String, iconResId: Int, isSelected: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = onClick)
            .background(if (isSelected) Color.LightGray else Color.Transparent),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = text, modifier = Modifier.weight(1f))
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
        )
    }
}
