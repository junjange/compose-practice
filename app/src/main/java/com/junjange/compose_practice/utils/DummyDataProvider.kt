package com.junjange.compose_practice.utils


data class RandomUser(
    val name: String = "내 이름은 조준장",
    val description: String = "Jetpack compose 뿌시기",
    val profileImagePath : String = "https://em-content.zobj.net/thumbs/240/apple/325/grinning-squinting-face_1f606.png"
)

object DummyDataProvider {
    val userList: List<RandomUser> = List<RandomUser>(200){
        RandomUser()
    }
}