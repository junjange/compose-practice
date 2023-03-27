package com.junjange.compose_practice

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.junjange.compose_practice.ui.theme.Compose_practiceTheme
import com.junjange.compose_practice.utils.DummyDataProvider
import com.junjange.compose_practice.utils.RandomUser

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose_practiceTheme {
                ContentView()
            }
        }
    }
}

@Composable
fun ContentView() {
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
    ) {
        Scaffold(
            backgroundColor = Color.White,
            topBar = { MyAppBar() }
        )
        {
            RandomUserList(randomUser = DummyDataProvider.userList)
        }

    }
}

@Composable
fun MyAppBar() {
    TopAppBar(
        elevation = 10.dp,
        backgroundColor = MaterialTheme.colors.background,
        modifier = Modifier.height(58.dp)
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterVertically),
            fontSize = 18.sp,
            fontWeight = FontWeight.Black,
        )
    }
}

@Composable
fun RandomUserList(randomUser: List<RandomUser>) {
    /**
     * 메모리 관리가 들어간 LazyColumn
     * 리사이클러뷰와 비슷
     * */
    LazyColumn() {
        items(randomUser) {
            /**
             * 각각의 아이템뷰를 설정
             * 리사이클러뷰에서 뷰홀더를 통해 아이템의 레이아웃을 설정한 것과 동일
             * */
            RandomUserView(it)

        }
    }
}

@Composable
fun RandomUserView(randomUser: RandomUser) {
    val typography = MaterialTheme.typography

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        elevation = 10.dp,
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)

        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_person_24),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(width = 50.dp, height = 50.dp)
                    .clip(CircleShape)
            )
            
            ProfileImg(profileImgPath = randomUser.profileImagePath
            )
            Column() {
                Text(
                    text = randomUser.name,
                    style = typography.subtitle1
                )
                Text(
                    text = randomUser.description,
                    style = typography.body1
                )

            }
        }

    }

}

@SuppressLint("UnrememberedMutableState")
@Composable
fun ProfileImg(profileImgPath: String, modifier: Modifier = Modifier) {
    // 이미지 비트맵
    val bitmap: MutableState<Bitmap?> = mutableStateOf(null)

    val imageModifier = modifier
        .size(width = 50.dp, height = 50.dp)
        .clip(CircleShape)

    Glide.with(LocalContext.current)
        .asBitmap()
        .load(profileImgPath)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {

                bitmap.value = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {
            }

        })

    // 비트맵이 있다면
    bitmap.value?.asImageBitmap()?.let {
        Image(
            bitmap = it,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = imageModifier
        )
    } ?: Image(
        painter = painterResource(id = R.drawable.ic_baseline_person_24),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = imageModifier
    )
    
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Compose_practiceTheme {
        ContentView()
    }
}