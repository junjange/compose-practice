package com.example.presentation.ui.main

import com.example.domain.model.Post
import com.example.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject


/**
 * mvi란?(https://www.charlezz.com/?p=46365)
 * mvi를 적용하기 위해 orbit 라이브러리를 사용했다. (https://www.charlezz.com/?p=46377)
 * Orbit은 안드로이드 뿐만 멀티플랫폼을 지원하는 Redux/MVI 같은 라이브러리 이며, 쉽고 가벼운 것이 특징이다.
 *
 * Orbit은 다음과 같은 특징을 가진다.
 * - 쉽고, 안전한 타입, 코루틴 스타일 및 확장 API 지원
 * - iOS 및 안드로이드를 타게팅한 멀티플랫폼 지원
 * - 코틀린 코루틴 완벽 지원
 * - 생명주기에 안전한 flow 수집
 * - SavedState를 포함한 ViewModel 지원
 * - 간단한 단위 테스트 지원
 * - 내장된 Espresso 유휴 자원 지원
 * - RxJava 및 LiveData와 호환 지원
 *
 * Orbit 을 써야 하는 이유
 * MVI 패턴을 구현하기 위해서 별도의 라이브러리 또는 프레임워크가 필수는 아니지만, 내가 Orbit을 선택한 이유는 다음과 같다.
 * - MVI의 개념을 그대로 따름
 * - 타 라이브러리에 비해 배우기 쉬움
 * - 보일러플레이트 제거
 * - 사용하기 쉬운 코루틴
 *
 * Orbit은 상태(State)와 부수효과(Side Effects)를 관리하는 Container라는 개념을 정의하고 사용하고 있다.
 * 일반적으로 ViewModel이 Container Host가 되어 Container를 관리하게 되는데,
 * 이로 인해 상태 및 부수효과를 다루기가 쉬워지고, 보일러플레이트를 감소 시킬 수 있다.
 *
 * ViewModel에서 컨테이너 활용을 위해 다음과 같이 코드를 작성한다.
 * 아래 코드의 내용은 다음과 같다.
 * - 일반적으로 안드로이드에서는 ViewModel을 ContainerHost(인터페이스)로 구현한다.
 * - 위 인터페이스를 구현하게 되면 container를 생성해야하는데, container<State, SideEffect>(…) 팩토리 함수를 활용할 수 있다.
 * - intent, reduce, postSideEffect 와 같은 dsl을 활용하여 상태 및 부수효과를 변경한다.
 *
 * orbit에서 사용하는 DSL의 의미는 다음과 같다
 * - intent : 컨테이너 내에 있는 상태 및 부수효과를 변경하기 위한 빌드 함수
 * - reduce : 현재 상태와 들어온 이벤트를 토대로 새로운 상태를 만들어 낸다.
 * - postSideEffect : 상태 변경과 관련 없는 이벤트들을 처리하기 위한 부수효과를 발생 시킨다.
 *
 * ViewModel(ContainerHost)을 Activity 또는 Fragment 에 연결한다.
 * viewModel.container 를 통해 stateFlow(상태)와 sideEffectFlow(부수효과)에 접근 할 수 있으며,
 * 이를 직접 수집하거나 orbit-viewmodel 모듈 의존성 추가를 통해 손쉽게 viewModel.observe(…)를 호출 할 수도 있다.
 */

@HiltViewModel
class MainViewModel @Inject constructor(
) : ContainerHost<MainState, MainSideEffect>, BaseViewModel() {

    override val container = container<MainState, MainSideEffect>(MainState())

    fun onSelectPost(post: Post) = intent {
        reduce { state.copy(selectedPost = post) }
        // TODO: Check is this post created by me
    }
}
