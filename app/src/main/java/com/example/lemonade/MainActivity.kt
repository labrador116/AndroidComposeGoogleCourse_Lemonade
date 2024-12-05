package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                ShowLemonadeGame()
            }
        }
    }
}

@Composable
fun ShowLemonadeGame() {
    var step by remember { mutableIntStateOf(1) }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        when (step) {
            1 -> ChangeStep(
                painterResource(R.drawable.lemon_tree),
                stringResource(R.string.descr_1)
            ) { step = 2 }

            2 -> {
                val randomClick = (1..3).random()
                var currentClick = 0
                ChangeStep(
                    painterResource(R.drawable.lemon_squeeze),
                    stringResource(R.string.descr_2)
                ) {
                    currentClick++
                    if (currentClick >= randomClick) {
                        step = 3
                    }
                }
            }

            3 -> ChangeStep(
                painterResource(R.drawable.lemon_drink),
                stringResource(R.string.descr_3)
            ) { step = 4 }

            4 -> ChangeStep(
                painterResource(R.drawable.lemon_restart),
                stringResource(R.string.descr_4)
            ) { step = 1 }

            else -> ChangeStep(
                painterResource(R.drawable.lemon_tree),
                stringResource(R.string.descr_1)
            ) { step = 1 }
        }
    }
}

@Composable
fun ChangeStep(painter: Painter, descr: String, nextStepFunc: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painter,
            contentDescription = descr,
            Modifier
                .wrapContentSize()
                .border(
                    2.dp,
                    color = Color(red = 105, green = 205, blue = 216),
                    shape = RoundedCornerShape(4.dp)
                )
                .clickable {
                    nextStepFunc()
                }
        )
        Spacer(Modifier.height(32.dp))

        Text(
            descr,
            fontSize = 18.sp
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun LemonadePreview() {
    ShowLemonadeGame()
}