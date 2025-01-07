// BattleMenuViewModel.kt
package com.example.a3gamebrogemon.views.models

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a3gamebrogemon.classes.Brogemon
import com.example.a3gamebrogemon.classes.Move
import com.example.a3gamebrogemon.classes.Type
import com.example.a3gamebrogemon.classes.brogemons
import kotlinx.coroutines.delay

/**
 * Chooses the best move for the enemy to exploit the player's weakness.
 */
fun chooseBestMove(enemy: Brogemon, player: Brogemon): Move {
    val playerWeakness = player.type.firstOrNull() // Player's first type as weakness

    // Ensure the weakness is passed as a Set<Type>
    val playerWeaknessSet = if (playerWeakness != null) setOf(playerWeakness) else emptySet()

    // Sort enemy's moves by potential damage
    val sortedMoves = enemy.moves.sortedByDescending { move ->
        val multiplier = Type.calculateDamageMultiplier(move.type, playerWeaknessSet)
        move.power * multiplier
    }

    // Add a small chance for randomness (e.g., 33%)
    return if ((1..100).random() <= 67) {
        sortedMoves.first()
    } else {
        enemy.moves.random()
    }
}

// Define states
enum class BattleState {
    Idle,
    PlayerAttacks,
    ShowPlayerAttackText,
    EnemyAttacks,
    ShowEnemyAttackText,
    End
}

@Composable
fun BattleMenuScreen(selectedBrogemon: Brogemon) {
    var enemyBrogemon by remember { mutableStateOf(brogemons.random()) }
    var battleState by remember { mutableStateOf(BattleState.Idle) }
    var displayText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Player's Brogemon Info
        BrogemonInfo("Your Brogemon", selectedBrogemon)
        // Enemy's Brogemon Info
        BrogemonInfo("Enemy Brogemon", enemyBrogemon)

        // Move Buttons
        if (battleState == BattleState.Idle) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                selectedBrogemon.moves.forEachIndexed { index, _ ->
                    MoveButton(
                        defender = enemyBrogemon,
                        attacker = selectedBrogemon,
                        move = index,
                        onMoveSelected = {
                            battleState = BattleState.PlayerAttacks
                        }
                    )
                }
            }
        }

        // Display text
        if (displayText.isNotEmpty()) {
            BasicText(text = displayText)
        }
    }

    // Handle state transitions
    LaunchedEffect(battleState) {
        when (battleState) {
            BattleState.PlayerAttacks -> {
                // Player attacks enemy
                val playerMove = selectedBrogemon.moves.random() // Example, replace with chosen move
                val enemyMove = chooseBestMove(enemyBrogemon, selectedBrogemon)
                applyDamage(selectedBrogemon, enemyBrogemon, playerMove)

                // # add animation here for Player attack
                displayText = "${selectedBrogemon.name} used ${playerMove.name}!"
                battleState = BattleState.ShowPlayerAttackText
            }

            BattleState.ShowPlayerAttackText -> {
                delay(2000) // Wait 2 seconds
                if (enemyBrogemon.health.intValue > 0) {
                    battleState = BattleState.EnemyAttacks
                } else {
                    displayText = "${enemyBrogemon.name} fainted!"
                    delay(2000)
                    enemyBrogemon = brogemons.random() // Replace defeated enemy
                    enemyBrogemon.resetStats()
                    selectedBrogemon.gainExperience(enemyBrogemon.level.intValue)
                    battleState = BattleState.Idle
                }
            }

            BattleState.EnemyAttacks -> {
                val enemyMove = chooseBestMove(enemyBrogemon, selectedBrogemon)
                applyDamage(enemyBrogemon, selectedBrogemon, enemyMove)

                // # add animation here for Enemy attack
                displayText = "${enemyBrogemon.name} used ${enemyMove.name}!"
                battleState = BattleState.ShowEnemyAttackText
            }

            BattleState.ShowEnemyAttackText -> {
                delay(2000) // Wait 2 seconds
                if (selectedBrogemon.health.intValue <= 0) {
                    displayText = "${selectedBrogemon.name} fainted! Game over!"
                    delay(2000)
                    battleState = BattleState.End
                } else {
                    battleState = BattleState.Idle
                }
            }

            BattleState.End -> {
                // End of battle
                displayText = "Battle Over!"
            }

            else -> {}
        }
    }
}

@Composable
fun BrogemonInfo(title: String, brogemon: Brogemon) {
    val hpPercentage = brogemon.health.intValue.toFloat() / brogemon.maxHealth
    val animatedHp by animateFloatAsState(
        targetValue = hpPercentage,
        animationSpec = tween(durationMillis = 500) // Smooth lowering over 500ms
    )

    Column(horizontalAlignment = Alignment.Start) {
        Row { BasicText("$title: ${brogemon.name}") }
        Row {
            BasicText("Health: ${brogemon.health.intValue} / ${brogemon.maxHealth}")
            BasicText(" | Level: ${brogemon.level.intValue} (${brogemon.experience.intValue})")
        }
        Row {
            HPBar(
                hpPercentage = animatedHp,
                modifier = Modifier
                    .fillMaxWidth(0.8f) // Adjust bar width
                    .height(20.dp)
            )
        }
        Row { BasicText("Type: ${brogemon.type.joinToString()}") }
    }
}

@Composable
fun HPBar(hpPercentage: Float, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            // Background bar (full size)
            drawRoundRect(
                color = Color.Gray,
                cornerRadius = cornerRadius(size.height)
            )
            // Foreground bar (based on current HP percentage)
            drawRoundRect(
                color = Color.Red,
                size = size.copy(width = size.width * hpPercentage),
                cornerRadius = cornerRadius(size.height)
            )
        }
    }
}

// Helper function for consistent corner radius
fun cornerRadius(height: Float) = androidx.compose.ui.geometry.CornerRadius(height / 2)

@Composable
fun MoveButton(
    defender: Brogemon,
    attacker: Brogemon,
    move: Int,
    onMoveSelected: () -> Unit
) {
    Button(
        onClick = onMoveSelected
    ) {
        BasicText(text = attacker.moves[move].name)
    }
}

// Apply damage function
fun applyDamage(attacker: Brogemon, defender: Brogemon, move: Move) {
    val rawDamage = move.power
    val multiplier = Type.calculateDamageMultiplier(move.type, defender.type)
    val actualDamage = (rawDamage * multiplier / defender.defense).toInt().coerceAtLeast(1) // Minimum damage is 1
    defender.health.intValue = (defender.health.intValue - actualDamage)
}

@Preview
@Composable
fun BattleMenuScreenPreview() {
    BattleMenuScreen(
        brogemons[0])
}
