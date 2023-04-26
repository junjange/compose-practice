package com.example.presentation.ui.base

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import com.example.presentation.ui.base.ActivityTransition.overridePendingPopTransition
import com.example.presentation.ui.base.ActivityTransition.overridePendingTransition

abstract class BaseActivity : ComponentActivity() {

    protected inline val TAG get() = this::class.java.simpleName
    protected open val transitionAnimation = ActivityTransition.Animation.Push

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(transitionAnimation)
    }

    protected open fun handleException(throwable: Throwable) {
        Log.e(TAG, throwable.stackTraceToString())
        finish()
    }

    // 에러 발생 시 RuntimeException 발생시키고 전환 에니메이션
    override fun finish() {
        super.finish()
        overridePendingPopTransition(transitionAnimation)
    }
}
