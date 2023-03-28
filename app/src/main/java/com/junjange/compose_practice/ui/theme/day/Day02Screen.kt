package com.junjange.compose_practice.ui.theme.day

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.junjange.compose_practice.R
import com.junjange.compose_practice.ui.theme.Compose_practiceTheme
import java.util.*


@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface
        ),
        placeholder = {
            Text(text = "Search")
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
    )
}

@Composable
fun AlignYourBodyElement(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = drawable),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            text = stringResource(id = text),
            style = MaterialTheme.typography.h3,
            modifier = Modifier.paddingFromBaseline(
                top = 24.dp,
                bottom = 8.dp
            )
        )
    }
}

@Composable
fun FavoriteCollectionCard(
    @StringRes text: Int,
    @DrawableRes drawable: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.small,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(192.dp)
        ) {
            Image(
                painter = painterResource(id = drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(56.dp)
            )
            Text(
                text = stringResource(id = text),
                style = MaterialTheme.typography.h3,
                modifier = modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun FavoriteCollectionCardPreview() {
    Compose_practiceTheme {
        FavoriteCollectionCard(
            text = R.string.my_name,
            drawable = R.drawable.ic_baseline_person_24,
            modifier = Modifier.padding((8.dp))

        )
    }
}

private val alignYourBodyData = listOf(
    R.drawable.ic_baseline_person_24 to R.string.app_name,
    R.drawable.ic_baseline_person_24 to R.string.app_name,
    R.drawable.ic_baseline_person_24 to R.string.app_name,
    R.drawable.ic_baseline_person_24 to R.string.app_name,
    R.drawable.ic_baseline_person_24 to R.string.app_name,
    R.drawable.ic_baseline_person_24 to R.string.app_name
).map { DrawableStringPair(it.first, it.second) }

private data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)

//@Composable
//fun AlignYourBodyRow(
//    modifier: Modifier = Modifier
//) {
//    LazyRow(
//        horizontalArrangement = Arrangement.spacedBy(8.dp),
//        contentPadding = PaddingValues(horizontal = 16.dp),
//        modifier = modifier
//    ){
//        items(alignYourBodyData){ item ->
//            AlignYourBodyElement(drawable = item, text = , modifier = )
//
//        }
//    }
//
//}
private val favoriteCollectionsData = listOf(
    R.drawable.ic_baseline_person_24 to R.string.app_name,
    R.drawable.ic_baseline_person_24 to R.string.app_name,
    R.drawable.ic_baseline_person_24 to R.string.app_name,
    R.drawable.ic_baseline_person_24 to R.string.app_name,
    R.drawable.ic_baseline_person_24 to R.string.app_name,
    R.drawable.ic_baseline_person_24 to R.string.app_name
).map { DrawableStringPair(it.first, it.second) }

//@Composable
//fun FavoriteCollectionCard(
//    modifier: Modifier = Modifier
//) {
//    LazyHorizontalGrid(
//        rows = GridCells.Fixed(2),
//        modifier = modifier
//    ) {
//        items(favoriteCollectionsData) { item ->
//            FavoriteCollectionCard(
//                text = item.text,
//                drawable = item.drawable,
//                modifier = modifier.padding(56.dp)
//            )
//        }
//    }
//}

@Composable
fun HomeSection(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = title)
                .uppercase(Locale.getDefault()),
            style = MaterialTheme.typography.h2,
            modifier = modifier
                .paddingFromBaseline(top = 40.dp, bottom = 8.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }
}


@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(vertical = 16.dp)
    ) {
        Spacer(modifier = modifier.height(16.dp))
        SearchBar(modifier = modifier.padding(horizontal = 16.dp))
        HomeSection(title = R.string.app_name) {
//            AlignYourBodyRow()
        }
        HomeSection(title = R.string.app_name) {
//            FavoriteCollectionsGrid()
        }
        Spacer(modifier = modifier.height(16.dp))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2, heightDp = 180)
@Composable
fun ScreenContentPreview() {
    Compose_practiceTheme {
        HomeScreen()
    }
}


@Composable
fun SootheBottomNavigation(modifier: Modifier = Modifier) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.background,
        modifier = modifier
    ) {
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null
                )
            },
            label = {
                Text(stringResource(R.string.bottom_navigation_home))
            },
            selected = true,
            onClick = {}
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },
            label = {
                Text(stringResource(R.string.bottom_navigation_profile))
            },
            selected = false,
            onClick = {}
        )
    }
}