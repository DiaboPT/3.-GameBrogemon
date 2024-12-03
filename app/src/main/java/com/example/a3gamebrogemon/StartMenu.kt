package com.example.a3gamebrogemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StartMenu(
    modifier: Modifier = Modifier,
    onPlayClick: () -> Unit = {}
){
    var brogemon_selection_menu by remember { mutableStateOf(false) }
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(
                id = R.drawable.tile_grass_ground
            ),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        if(!brogemon_selection_menu){
            Column(
                modifier = Modifier
                    .padding(
                        32.dp
                    )
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Brogemon KT",
                    modifier = Modifier
                        .background(
                            color = Color.hsv(0f,0f,0f, .5f)
                        )
                    ,
                    fontSize = 32.sp,
                    textAlign = TextAlign.Center,
                    color = Color.hsl(0f,0f,1f)
                )

                Spacer(modifier = Modifier.height(32.dp))

                Image(
                    painter = painterResource(R.drawable.brog_charader),
                    contentDescription = null,
                )

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    modifier = Modifier
                        .width(
                            200.dp
                        )
                        .height(
                            50.dp
                        ),
                    onClick = {
                        brogemon_selection_menu =
                            true
                        println(brogemon_selection_menu)
                    }
                ) {
                    Text(
                        text = "Play Now"
                    )
                }
            }
        }
        else{
            SelectBrogemonMenu(onPlayClick)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StartMenuPreview() {
    StartMenu()
}