package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize() ,
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}






@Composable
fun LemonadeApp() {
    var currentStep by remember { mutableStateOf(1)}

    var squezzeCount by remember {
        mutableStateOf(0)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when (currentStep) {
            1 -> LemonTextAndImage(
                image = R.drawable.lemon_tree,
                string = R.string.act1,
                description = R.string.img1,
                onImageClick = {
                    currentStep = 2
                    squezzeCount = (2..4).random() // in here squezzeCount it assign the number for the clickable squeeze
                }
            )
            2 -> LemonTextAndImage(
                image = R.drawable.lemon_squeeze,
                string = R.string.act2,
                description = R.string.img2,
                onImageClick = {
                    squezzeCount--
                        // when the image is clicked the squeezeCount will decreased and if it gets to 0 it will go to the next step
                    if (squezzeCount == 0) {
                        currentStep = 3
                    }
                }
            )
            3 -> LemonTextAndImage(
                image = R.drawable.lemon_drink,
                string = R.string.act3 ,
                description = R.string.img3,
                onImageClick = {currentStep = 4}
            )
            else -> LemonTextAndImage(
                image = R.drawable.lemon_restart,
                string = R.string.act4 ,
                description = R.string.img4,
                onImageClick = {currentStep = 1}
            )
        }
    }

}

// This is the function that create the image and text
@Composable
fun LemonTextAndImage(
    image: Int,
    string: Int,
    description: Int,
    onImageClick: () -> Unit // This is the Click Behavior Parameter
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = stringResource(id = string))
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(image) ,
            contentDescription = stringResource(id = description),
            modifier = Modifier
                .wrapContentSize()
                .border(
                    BorderStroke(2.dp , Color(105 , 205 , 216)) ,
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(16.dp)
                .clickable(onClick = onImageClick)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadePreview() {
    LemonadeTheme {
        LemonadeApp()
    }
}