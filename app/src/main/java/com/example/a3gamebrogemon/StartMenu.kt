package com.example.a3gamebrogemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun StartMenu(
    modifier: Modifier = Modifier,
    onPlayClick: () -> Unit = {}
){
    var brogemon_selection_menu by remember { mutableStateOf(false) }
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            painter = painterResource(
                id = R.drawable.grass_ground
            ),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        if(!brogemon_selection_menu){
            Column(
                modifier = Modifier.padding(
                    40.dp
                ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(
                        id = R.drawable.ic_launcher_foreground
                    ),
                    contentDescription = "play now",
                    modifier = Modifier
                        .width(
                            200.dp
                        )
                        .height(
                            200.dp
                        )
                        .clickable {
                            brogemon_selection_menu =
                                true
                            println(brogemon_selection_menu)
                        },
                    contentScale = ContentScale.FillBounds
                )
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