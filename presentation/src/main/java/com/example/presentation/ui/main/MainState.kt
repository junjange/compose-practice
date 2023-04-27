package com.example.presentation.ui.main

import com.example.domain.model.Post

data class MainState(
    val selectedPost: Post? = null
)

sealed class MainSideEffect {

}
