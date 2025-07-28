package com.the43h1.woofgoogle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
                        items(Data.getData()){
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

    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
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
                            .rotate(if (isExpanded) 180f else 0f)
                            .animateContentSize(
                                // TODO(Wanna Animate This)
                            )
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