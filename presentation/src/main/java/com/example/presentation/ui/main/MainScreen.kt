package com.example.presentation.ui.main

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.compose_practice.ui.R
import com.example.presentation.ui.component.FimoBottomBar
import com.example.presentation.ui.component.FimoBottomSheetContent
import com.example.presentation.ui.component.NoRippleInteractionSource
import com.example.presentation.ui.component.bottomPanelHeight
import com.example.presentation.ui.theme.ComposePracticeTheme
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectAsState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
// @OptIn 어노테이션을 사용하여 명시적으로 ‘안정되지 않은 버전의 API를 사용하는데 동의한다’ 라 선언해 준 것
@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalMaterialApi::class
)
@Composable
fun MainScreen(
    viewModel: MainViewModel,
    startUploadActivity: () -> Unit,
    startFriendActivity: () -> Unit,
    startSettingsActivity: () -> Unit,
) {
    val state = viewModel.collectAsState().value

    /**
     * NavController는 Navigation 구성요소의 중심 API로, 스테이트풀(Stateful)이며 앱의 화면과 각 화면 상태를 구성하는 컴포저블의 백 스택을 추적합니다.
     * 컴포저블에서 rememberNavController() 메서드를 사용하여 NavController를 만들 수 있습니다.
     * 컴포저블 계층 구조에서 NavController를 만드는 위치는 이를 참조해야 하는 모든 컴포저블이 액세스할 수 있는 곳이어야 합니다.
     * 이는 상태 호이스팅의 원칙을 준수하며, 이렇게 하면 NavController와 상태를 사용할 수 있습니다.
     * 상태는 currentBackStackEntryAsState()를 통해 제공되며 화면 외부에서 컴포저블을 업데이트하기 위한 정보 소스로 사용됩니다.
     *
     * BottomNavigation 컴포저블에서 currentBackStackEntryAsState() 함수를 사용하여 현재 NavBackStackEntry를 가져옵니다.
     * 이 항목을 통해 현재 NavDestination에 액세스할 수 있습니다.
     * 그러면 중첩된 탐색을 사용하고 있는 경우를 처리하도록 NavDestination 계층 구조를 통해 항목의 경로와 현재 대상 및 그 상위 대상의 경로를 비교하여
     * 각 BottomNavigationItem의 선택된 상태를 확인할 수 있습니다.
     * */
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination // 현재 사용자에게 표시되는 목적지
    val navigator = rememberNavigator(navController = navController)

    /**
     * Composable에서 올바른 CoroutineScope을 선택하는 것이 중요한 이유
     * Composable 내부에서 코루틴을 수행할 경우 Composable에 대한 Recomposition이 일어날 때 정리되어야 하는 Coroutine이 정리가 안된 상태로 계속해서 Coroutine이 쌓일 수 있다.
     * Recomposition은 자주 일어나는 동작이므로 Recomposition 마다 Coroutine 을 생성하는 것은 위험하며 심할 경우 앱 크래시를 발생시킬 수도 있다.
     * 따라서 Composable에서 Coroutine을 생성한다면 Recomposition이 일어날 때 취소되어야 한다. (꼭 그렇지 않은 경우도 있지만 그 래야 하는 경우가 대부분이다)
     * Compose는 이를 위해 Composable의 Lifecycle을 따르는 CoroutineScope을 반환하는 rememberCoroutineScope() 함수를 제공한다.
     *
     * rememberCoroutineScope은 Composable의 생명주기를 따르는 CoroutineScope이다.
     * 따라서 Coroutine은 rememberCoroutineScope의 범위에서 실행시켜 Composable이 파괴될 때 파괴해야 한다.
     * */
    val coroutineScope = rememberCoroutineScope()


    // 하단 시트의 상태
    val modalBottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    var isSelectedPostIsMine by remember { mutableStateOf(false) }

    val bottomSheetTextStyle = ComposePracticeTheme.typography.regular.copy(
        fontSize = 18.sp,
        color = ComposePracticeTheme.colors.black
    )

    /**
     * BackHandler는 androidx.activity.compose에 내장돼 있는 함수이다.
     * 뒤로가기 이벤트가 감지 됐을 때 enabled이 true면 onBack() 을 실행하게 된다.
     * */
    BackHandler(enabled = modalBottomSheetState.isVisible) {
        coroutineScope.launch { modalBottomSheetState.hide() }
    }

    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetContent = {
            FimoBottomSheetContent {
                if (isSelectedPostIsMine) {
                    TextButton(
                        onClick = {},
                        interactionSource = NoRippleInteractionSource
                    ) {
                        Text(
                            text = stringResource(id = R.string.edit),
                            style = bottomSheetTextStyle
                        )
                    }
                    TextButton(
                        onClick = {},
                        interactionSource = NoRippleInteractionSource
                    ) {
                        Text(
                            text = stringResource(id = R.string.delete),
                            style = bottomSheetTextStyle
                        )
                    }
                } else {
                    TextButton(
                        onClick = {},
                        interactionSource = NoRippleInteractionSource
                    ) {
                        Text(
                            text = stringResource(id = R.string.report),
                            style = bottomSheetTextStyle
                        )
                    }
                }
            }
        }
    ) {
        Scaffold(
            bottomBar = {
                FimoBottomBar(
                    currentDestination = currentDestination,
                    onNavigate = { navigator.navigateTo(it) },
                    onActionClick = startUploadActivity,
                    profileImageUrl = "https://avatars.githubusercontent.com/u/72238126?v=4"
                )
            },
        ) {
            NavHost(
                navController = navController,
                startDestination = Destination.Home.route,
                modifier = Modifier.padding(bottom = bottomPanelHeight)
            ) {
                composable(Destination.Home.route) {
//                    HomeScreen(
//                        onClickMore = {
//                            viewModel.onSelectPost(it)
//                            coroutineScope.launch { modalBottomSheetState.show() }
//                        },
//                        startSettingsActivity = startSettingsActivity
//                    )
                }
                composable(Destination.Feed.route) {
//                    FeedScreen(
//                        onClickMore = {
//                            viewModel.onSelectPost(it)
//                            coroutineScope.launch { modalBottomSheetState.show() }
//                        },
//                        startFriendActivity = startFriendActivity,
//                        startSettingsActivity = startSettingsActivity
//                    )
                }
            }
        }
    }
}
