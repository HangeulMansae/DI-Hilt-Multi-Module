package com.fastcampus.presentation.main

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.fastcampus.presentation.theme.ConnectedTheme
import com.fastcampus.presentation.writing.WritingActivity

@Composable
fun MainBottomBar(
    navController: NavController
) {
    val context = LocalContext.current
    // 현재 NavController의 백스택의 맨 위에 있는 NavBackStackEntry를 State 객체로 반환
    // 스택의 각 화면이 BackStackEntry로 표현됨
    // Navigation의 백스택이 바뀔 때마다 자동으로 업데이트 됨
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    // Destination이란 Navigation Graph에서 정의된 특정 목적지를 의미
    // EX: 화면, Fragment, Composable 등
    // Navigation Graph에서 Home과 Profile이라는 두개의 목적지를 설정했을 때,
    // 현재 보고 있는 화면에 해당하는 목적지가 navBackStackEntry.destination에 담김
    // NavController의 navBackstackEntry에서 어딜 보고 있는 건지 알기 위해서,
    // destination이 가리키는 route가 미리 정의해돈 MainRoute의 무엇에 해당하는지 알아내어 currentRoute에 저장
    val currentRoute = navBackStackEntry?.destination?.route?.let { currentRoute ->
        MainRoute.values().find { route -> route.route == currentRoute }
    } ?: MainRoute.BOARD

    MainBottomBar(
        currentRoute = currentRoute,
        onItemClick = { newRoute ->
            if(newRoute == MainRoute.WRITING){
                // 글쓰기면 Writing Activity로 전환
                context.startActivity(
                    Intent(context, WritingActivity::class.java)
                )
            }else{
                // 그 외는 누른 해당 Route로 이동
                navController.navigate(route = newRoute.route) {
                    navController.graph.startDestinationRoute?.let {
                        popUpTo(it){
                            saveState = true
                        }
                    }
                    this.launchSingleTop = true
                    this.restoreState = true
                }
            }
        }
    )
}

@Composable
private fun MainBottomBar(
    currentRoute: MainRoute,
    onItemClick: (MainRoute) -> Unit
) {
    Column {
        Divider()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MainRoute.values().forEach { route ->
                IconButton(onClick = { onItemClick(route) }) {
                    Icon(
                        imageVector = route.icon,
                        contentDescription = route.contentDescription,
                        tint = if (currentRoute == route) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            Color.White
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun MainBottomBarPreview() {
    ConnectedTheme {
        Surface {
            var currentRoute by remember {mutableStateOf(MainRoute.BOARD)}
            MainBottomBar(
                currentRoute = currentRoute,
                onItemClick = { newRoute -> currentRoute = newRoute}
            )
        }
    }
}