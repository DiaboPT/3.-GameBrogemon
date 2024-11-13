package com.example.a3gamebrogemon

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.viewinterop.AndroidView
import com.example.a3gamebrogemon.GameView

@Composable
fun GameScreenView () {

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp

    val density = configuration.densityDpi / 160f
    val screenWidthPx = screenWidth * density
    val screenHeightPx = screenHeight * density

    AndroidView(factory = { context ->
            GameView(context = context,
                width = screenWidthPx.toInt(),
                height = screenHeightPx.toInt() )
        },
        update = {
            it.resume()
        }
    )
}