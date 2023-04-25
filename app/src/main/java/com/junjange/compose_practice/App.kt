package com.junjange.compose_practice

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp

/**
 * Hilt를 사용하는 모든 앱은 @HiltAndroidApp으로 주석이 지정된 Application 클래스를 포함해야 한다.
 * @HiltAndroidApp 어노테이션에 의해 Singleton Component를 생성하게 된다.
 * @HiltAndroidApp은 애플리케이션 수준 종속 항목 컨테이너 역할을 하는 애플리케이션의 기본 클래스를 비롯하여 Hilt의 코드 생성을 트리거한다.
 * 따라서 앱이 살아있는 동안 의존성(Dependency)을 제공하는 역할을 하는 애플리케이션(Application) 레벨의 Component가 되는 것이다.
 * 또한 Application 클래스를 생성한 뒤에는 AndroidManifest 파일에 application 태그에 name 설정을 꼭 해주어야 오류가 생기지 않는다.
 * */

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()

//        // Kakao
//        KakaoSdk.init(this, BuildConfig.KAKAO_API_KEY)

    }
}
