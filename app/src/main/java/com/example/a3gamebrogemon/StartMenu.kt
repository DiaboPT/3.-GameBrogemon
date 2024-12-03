package com.example.a3gamebrogemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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
    onPlayClick: () -> Unit
) {
    // State to track the current screen
    var currentScreen by remember { mutableStateOf("start") }
    var selectedBrogemon by remember { mutableStateOf<Brogemon?>(null) }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.tile_grass_ground),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        when (currentScreen) {
            "start" -> {
                // Main Start Screen
                Column(
                    modifier = Modifier
                        .padding(32.dp)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Brogemon KT",
                        modifier = Modifier.background(color = Color.hsv(0f, 0f, 0f, .5f)),
                        fontSize = 32.sp,
                        textAlign = TextAlign.Center,
                        color = Color.hsl(0f, 0f, 1f)
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Image(
                        painter = painterResource(R.drawable.brog_charader),
                        contentDescription = null,
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Button(
                        modifier = Modifier
                            .width(200.dp)
                            .height(50.dp),
                        onClick = {
                            currentScreen = "selection"
                        }
                    ) {
                        Text(text = "Play Now")
                    }
                }
            }
            "selection" -> {
                // Brogemon Selection Screen
                SelectBrogemonMenu(onPlayClick = { brogemon ->
                    selectedBrogemon = brogemon
                    currentScreen = "battle"
                })
            }
            "battle" -> {
                // Battle Screen
                selectedBrogemon?.let { brogemon ->
                    BattleScreen(
                        playerBrogemon = brogemon,
                        onMoveSelected = { move ->
                            println("Selected Move: ${move.name}")
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StartMenuPreview() {
    StartMenu(onPlayClick = {})
}
