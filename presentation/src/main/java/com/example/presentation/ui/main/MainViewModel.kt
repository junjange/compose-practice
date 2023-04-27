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
 * mvi를 적용하기 위해 orbit 라이브러리를 사용했다. (좋은 블로그 : https://www.charlezz.com/?p=46365)
 *
 *
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
