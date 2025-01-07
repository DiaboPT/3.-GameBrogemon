// BrogemonSelectionViewModel.kt
package com.example.a3gamebrogemon.views.models

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.a3gamebrogemon.classes.Brogemon
import com.example.a3gamebrogemon.classes.brogemons

@Composable
fun BrogemonSelectionScreen(onSelect: (Brogemon) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        brogemons.forEach { brogemon ->
            Button(onClick = { onSelect(brogemon) }) {
                BasicText(brogemon.name)
            }
        }
    }
}
