package com.example.presentation.ui.main

import com.example.domain.model.Post

/**
 * sudeEffect란 Composable 범위 밖에서 발생하는 앱 상태에 대한 변경이다.(https://kotlinworld.com/245)
 *
 * 실세계에서 View(Model(Intent())) 순수함수 구조로만 잘 순환하길 기대하지만 현실은 그렇지 못하다.
 * 간혹 상태를 변경할 필요가 없는 이벤트가 필요할 수도 있기 때문이다.
 * 예를 들면 Activity/Fragment 이동, Logging, Analytics, 토스트 노출 등이 그에 해당한다.
 * 그렇기 때문에 MVI를 언급할 때 일반적으로 Side Effects(부수효과)라는 개념을 써서 이를 처리 한다.
 *
 * Orbit 공식문서에 나온 예제를 기준으로 설명하자면 상태와 부수효과를 먼저 다음과 같이 정의한다
 * */
data class MainState(
    val selectedPost: Post? = null
)

sealed class MainSideEffect {

}
