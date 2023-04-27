package com.example.presentation.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.presentation.ui.base.BaseActivity
import com.example.presentation.ui.theme.ComposePracticeTheme
import dagger.hilt.android.AndroidEntryPoint


/**
 * UI가 구성 되는 것을 보면 component를 만들고
 * Screen에 각각의 component를 조합하여 화면을 구성한다.
 * 구성된 Screen은 activity or Fragment(이건 내 생각)에서 뿌려준다.
 * 또하느 화면 이동도 Intent와 navigation compose를 통해 수행된다.
 * 그렇다면 Flutter와 큰 차이는 없는 것으로 보인다.
 * 결국, component를 얼마나 예쁘고 사랑스럽게 잘 나누어 만드는 것에 따라
 * compose를 잘하는지, 못하는지가 정해지는 것인가아아아?!!
 *
 *
 * */

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**
         * setContent 람다 표현식으로 실제 화면에 노출하는 코드
         * 일반적으로 우리가 아는 Activity의 라이프사이클 콜백 onCreate()에서
         * setContentView(Int) 함수를 호출하던것이 setContent() 함수로 바뀐것이 가장 큰 특징으로 보여진다.
         * activity당 하나의 setContent를 갖는다.
         * */
        setContent {
            // 테마 설정
            ComposePracticeTheme {
                MainScreen(
                    viewModel = viewModel,
                    startUploadActivity = ::startUploadActivity,
                    startFriendActivity = ::startFriendActivity,
                    startSettingsActivity = ::startSettingsActivity,
                )
            }
        }
    }

    private fun startUploadActivity() {
//        UploadActivity.startActivity(this)
    }

    private fun startFriendActivity() {
//        FriendActivity.startActivity(this)
    }

    private fun startSettingsActivity() {
//        SettingsActivity.startActivity(this)
    }

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }
}

