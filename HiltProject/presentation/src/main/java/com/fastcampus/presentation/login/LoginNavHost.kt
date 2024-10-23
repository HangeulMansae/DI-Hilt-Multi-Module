package com.fastcampus.presentation.login

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions

@Composable
fun LoginNavHost() {
    // NavHost를 관리하기 위한 navController를 rememberNavController로 생성하면서 Composition 내에 저장
    // Composition이란 Composable에서 실제로 슬롯 테이블이 구현되고, Composition 과정에서 필요한 모든 정보들이 담기는 최상위 객체
    // Composition은 ★최상위 Composable마다 하나만 생성됨★
    // 최상위 Composable은 setContent임
    val navController = rememberNavController()
    // 네비게이션과 각종 설정들을 하는 곳
    // NavHost로 네비게이션을 위한 route들이 있는 것들을 관리하며,
    // navController를 세팅하고 startDestination과 같이 시작 위치를 설정
    NavHost(
        navController = navController,
        startDestination = LoginRoute.WelcomeScreen.name,
    ) {
        // 네비게이션으로 보여줄 @Composable 함수로 구현한 Content와 route를 설정
        composable(
            route = LoginRoute.WelcomeScreen.name
        ) {
            WelcomeScreen(
                // WelcomScreen 내 화면에서 무언가 버튼을 눌렀을 때 Login으로 Navigate 시키기 위한 함수 넘겨줌
                onNavigateToLoginScreen = {
                    navController.navigate(route = LoginRoute.LoginScreen.name)
                }
            )
        }

        composable(
            route = LoginRoute.LoginScreen.name
        ) {
            LoginScreen(onNavigateToSignUpScreen = {
                navController.navigate(LoginRoute.SignUpScreen.name)
            })
        }

        composable(
            route = LoginRoute.SignUpScreen.name
        ) {
            SignUpScreen(
                onNavigateToLoginScreen = {
                    navController.navigate(
                        route = LoginRoute.LoginScreen.name,
                        navOptions = navOptions{
                            popUpTo(LoginRoute.WelcomeScreen.name)
                        }
                    )
                }
            )
        }
    }
}