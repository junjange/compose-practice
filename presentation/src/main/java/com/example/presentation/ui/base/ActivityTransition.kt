package com.example.presentation.ui.base

import android.app.Activity
import androidx.annotation.AnimRes
import com.example.compose_practice.ui.R

/**
 * 애니메이션 전환을 위한 싱글톤 클래스
 * 사용자가 활동을 시작하거나 종료할 때 감지
 * overridePendingTransition 으로 애니메이션 효과가 있는 화면이동 구현
 * */

object ActivityTransition {

    enum class Animation(
        @AnimRes val enterAnimRes: Int,
        @AnimRes val exitAnimRes: Int,
        @AnimRes val popEnterAnimRes: Int,
        @AnimRes val popExitAnimRes: Int,
    ) {
        Cover(
            enterAnimRes = R.anim.cover_enter,
            exitAnimRes = R.anim.cover_exit,
            popEnterAnimRes = R.anim.cover_pop_enter,
            popExitAnimRes = R.anim.cover_pop_exit
        ),
        Push(
            enterAnimRes = R.anim.push_enter,
            exitAnimRes = R.anim.push_exit,
            popEnterAnimRes = R.anim.push_pop_enter,
            popExitAnimRes = R.anim.push_pop_exit
        );
    }


    fun Activity.overridePendingTransition(animation: Animation) =
        overridePendingTransition(animation.enterAnimRes, animation.exitAnimRes)

    fun Activity.overridePendingPopTransition(animation: Animation) =
        overridePendingTransition(animation.popEnterAnimRes, animation.popExitAnimRes)
}
