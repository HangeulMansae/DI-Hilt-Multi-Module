package com.fastcampus.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fastcampus.presentation.R
import com.fastcampus.presentation.board.BoardScreen
import com.fastcampus.presentation.setting.SettingScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNavHost() {
    val navController = rememberNavController()
    Surface {
        Scaffold(
            modifier = Modifier.windowInsetsPadding(WindowInsets.systemBars),
            topBar = {
                TopAppBar(
                    modifier = Modifier.background(MaterialTheme.colorScheme.background),
                    title = {
                        Text(text = stringResource(id = R.string.app_name))
                    })
            },
            content = {padding ->
                NavHost(
                    modifier = Modifier.padding(padding),
                    navController = navController,
                    startDestination = MainRoute.BOARD.route
                ){
                    composable(route = MainRoute.BOARD.route){
                        BoardScreen()
                    }
                    composable(route = MainRoute.SETTING.route){
                        SettingScreen()
                    }
                }
            },
            bottomBar =  {
                MainBottomBar(
                    navController = navController
                )
            }
        )
    }
}