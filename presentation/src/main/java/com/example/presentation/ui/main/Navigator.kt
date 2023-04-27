package com.example.presentation.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

/**
 * Navigator를 class로 만들어 사용
 * https://developer.android.com/jetpack/compose/navigation?hl=ko
 *
 * */
class Navigator(
    private val navController: NavController
) {

    fun navigateTo(destination: Destination) {
        /**
         * 탐색 그래프에서 구성 가능한 대상으로 이동하려면 navigate 메서드를 사용해야 합니다.
         * navigate는 대상의 경로를 나타내는 단일 String 매개변수를 사용합니다.
         * 탐색 그래프 내의 컴포저블에서 이동하려면 navigate를 호출하세요.
         * 기본적으로 navigate는 새 대상을 백 스택에 추가합니다.
         * 추가 탐색 옵션을 navigate() 호출에 연결하여 navigate 동작을 수정할 수 있습니다.
         * */
        navController.navigate(destination.route) {
            /**
             * 사용자가 항목을 선택할 때 백 스택에 많은 대상 스택이 쌓이지 않도록 그래프의 시작 대상으로 팝업
             * saveState 및 restoreState 플래그를 사용하면 하단 탐색 항목 간에 전환할 때 항목의 상태와 백 스택이 올바르게 저장되고 복원됩니다.
             * */
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // 동일한 항목을 다시 선택할 때 동일한 대상의 여러 복사본 방지
            launchSingleTop = true
            // 이전에 선택한 항목을 다시 선택할 때 상태 복원
            restoreState = true
        }
    }
}

@Composable
fun rememberNavigator(navController: NavController) =
    remember(navController) { Navigator(navController) }
