@file:OptIn(ExperimentalMaterial3Api::class)

package com.the43h1.woofgoogle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily.Companion.Serif
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.the43h1.woofgoogle.ui.theme.WoofGoogleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WoofGoogleTheme {
                Scaffold(topBar = {
                    WoofTopAppBar()
                }, modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LazyColumn(modifier = Modifier.padding(innerPadding)) {
                        items(Data.getData()) {
                            DogCard(it)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun WoofTopAppBar() {
    CenterAlignedTopAppBar(
        title = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painterResource(R.drawable.ic_woof_logo),
                    stringResource(R.string.app_name),
                    tint = Color.Unspecified
                )
//                                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    stringResource(R.string.app_name),
                    fontFamily = Serif,
                    fontStyle = MaterialTheme.typography.displayLarge.fontStyle,
                    fontSize = 26.sp
                )
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun DogCard(dogData: DogData, modifier: Modifier = Modifier) {
    var isExpanded by remember {
        mutableStateOf(false)
    }

    val upIcon by animateFloatAsState(
        targetValue = if(isExpanded) 180f else 0f,
    )

    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(250, easing = FastOutSlowInEasing)
            )
            .clickable(onClick = { isExpanded = !isExpanded }),
        shape = RoundedCornerShape(0.dp, 12.dp, 0.dp, 12.dp)
    ) {
        Column {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(8.dp)
                    .heightIn(max = 100.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(dogData.image),
                    contentDescription = stringResource(dogData.name),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .weight(1f)
                )
                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle()) {
                            append(stringResource(dogData.name))
                            append("\n${dogData.age} ${stringResource(R.string.years_old)}")
                        }
                    },
                    modifier = Modifier.weight(5f)

                )
                IconButton(
                    onClick = { isExpanded = !isExpanded },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Show More",
                        modifier = Modifier
                            .rotate(upIcon)
                            .weight(1f)
                    )
                }
            }
            if (isExpanded) {
                Text(
                    stringResource(dogData.about),
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WoofGoogleTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DogCard(
                DogData(
                    image = R.drawable.dog_1_koda,
                    name = R.string.dog_name_1,
                    about = R.string.dog_description_1,
                    age = 8
                )
            )
        }
    }
}