// BattleScreen.kt
package com.example.a3gamebrogemon

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BattleScreen(
    playerBrogemon: Brogemon,
    enemyBrogemon: Brogemon = brogemons.random(),
    onMoveSelected: (Move) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Enemy Brogemon Info
        Text(text = enemyBrogemon.name, style = MaterialTheme.typography.bodyMedium)
        ProgressBar(health = enemyBrogemon.health, maxHealth = enemyBrogemon.maxHealth)

        Spacer(modifier = Modifier.height(16.dp))

        // Player Brogemon Info
        Text(text = playerBrogemon.name, style = MaterialTheme.typography.bodyMedium)
        ProgressBar(health = playerBrogemon.health, maxHealth = playerBrogemon.maxHealth)

        Spacer(modifier = Modifier.height(32.dp))

        // Player's Move Buttons
        Text(text = "Choose a Move", style = MaterialTheme.typography.bodySmall)

        playerBrogemon.moves.forEach { move ->
            MoveButton(move = move, onMoveSelected = onMoveSelected)
        }
    }
}

@Composable
fun MoveButton(move: Move, onMoveSelected: (Move) -> Unit) {
    Button(
        onClick = { onMoveSelected(move) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(text = move.name)
    }
}

@Composable
fun ProgressBar(health: Int, maxHealth: Int) {
    LinearProgressIndicator(
        progress = health.toFloat() / maxHealth,
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
    )
}


@Preview(showBackground = true)
@Composable
fun BattleScreenPreview() {
    BattleScreen(
        playerBrogemon = brogemons[0],
        onMoveSelected = { move -> println("Selected Move: ${move.name}") }
    )
}
