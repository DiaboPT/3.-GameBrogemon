package com.example.a3gamebrogemon.classes

import android.os.Parcelable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import com.example.a3gamebrogemon.R
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.io.Serializable

data class Move(
    val name: String,
    val type: Type,
    val power: Int,
    val accuracy: Int
) : Serializable

enum class Type : Serializable {
    NORMAL, HOT, TREE, OCEAN, SAND, FREEZE, SHOCK;

    companion object {
        // Type effectiveness map
        private val typeEffectiveness = mapOf(
            HOT to mapOf(
                OCEAN to 0.5,  // Weak against OCEAN
                TREE to 2.0,   // Strong against TREE
                FREEZE to 2.0, // Strong against FREEZE
                SHOCK to 0.5,  // Weak against SHOCK
            ),
            TREE to mapOf(
                HOT to 0.5,    // Weak against HOT
                OCEAN to 2.0,  // Strong against OCEAN
                SAND to 0.5,   // Weak against SAND
                SHOCK to 2.0,  // Strong against SHOCK
            ),
            OCEAN to mapOf(
                HOT to 2.0,    // Strong against HOT
                TREE to 0.5,   // Weak against TREE
                SAND to 2.0,   // Strong against SAND
                FREEZE to 0.5, // Weak against FREEZE
            ),
            SAND to mapOf(
                TREE to 2.0,   // Strong against TREE
                OCEAN to 0.5,  // Weak against OCEAN
                FREEZE to 2.0, // Strong against FREEZE
                SHOCK to 0.5,  // Weak against SHOCK
            ),
            FREEZE to mapOf(
                HOT to 0.5,    // Weak against HOT
                OCEAN to 2.0,  // Strong against OCEAN
                SAND to 0.5,   // Weak against SAND
                SHOCK to 2.0,  // Strong against SHOCK
            ),
            SHOCK to mapOf(
                HOT to 2.0,    // Strong against HOT
                TREE to 0.5,   // Weak against TREE
                FREEZE to 0.5, // Weak against FREEZE
                SAND to 2.0,   // Strong against SAND
            ),
            NORMAL to mapOf() // NORMAL has no advantages/disadvantages
        )

        // Function to calculate damage multiplier
        fun calculateDamageMultiplier(attackerType: Type, defenderTypes: Set<Type>): Double {
            var multiplier = 1.0
            for (defenderType in defenderTypes) {
                multiplier *= typeEffectiveness[attackerType]?.get(defenderType) ?: 1.0
            }
            return multiplier
        }
    }
}

const val default_level: Int = 5

@Parcelize
data class Brogemon(
    val name: String,
    val type: @RawValue Set<Type>,
    val level: @RawValue MutableIntState,
    val experience: @RawValue MutableIntState,
    val baseHealth: Int,                     // Starting health at level 1
    val healthPerLevel: Int,                 // Health increase per level
    val health: @RawValue MutableIntState,   // Current health
    val defense: Int,
    val moves: @RawValue List<Move>,
    val speed: Int,
    var image: Int
) : Parcelable {

    // Computed max health based on level
    val maxHealth: Int
        get() = baseHealth + (level.intValue * healthPerLevel)

    // Reset stats method
    fun resetStats() {
        health.intValue = maxHealth
        level.intValue = default_level
        experience.intValue = 0
    }

    // Update level based on experience
    fun calculateLevel() {
        if (experience.intValue >= (level.intValue * level.intValue)) {
            level.intValue += 1
        }

    }

    fun gainExperience(amount: Int) {
        experience.intValue += amount
        calculateLevel()
    }
}

// Define moves
val scratch = Move(
    name = "Scratch",
    type = Type.NORMAL,
    power = 50,
    accuracy = 95
)
val flameThrower = Move(
    name = "Flame Thrower",
    type = Type.HOT,
    power = 90,
    accuracy = 85
)
val vineWhip = Move(
    name = "Vine Whip",
    type = Type.TREE,
    power = 70,
    accuracy = 90
)
val waterGun = Move(
    name = "Water Gun",
    type = Type.OCEAN,
    power = 80,
    accuracy = 90
)

// Define Brogemons
val brogemons = listOf(
    Brogemon(
        name = "Placeholder",
        type = setOf(Type.HOT, Type.TREE, Type.OCEAN, Type.SAND, Type.FREEZE, Type.SHOCK),
        level = mutableIntStateOf(default_level),
        experience = mutableIntStateOf(0),
        baseHealth = 200,
        healthPerLevel = 2,
        health = mutableIntStateOf(200 + (default_level * 2)),
        defense = 5,
        speed = 5,
        moves = listOf(scratch, flameThrower, vineWhip, waterGun),
        image = R.drawable.ic_launcher_foreground
    ),
    Brogemon(
        name = "Charader",
        type = setOf(Type.HOT),
        level = mutableIntStateOf(default_level),
        experience = mutableIntStateOf(0),
        baseHealth = 20,
        healthPerLevel = 2,
        health = mutableIntStateOf(20 + (default_level * 2)),
        defense = 5,
        speed = 5,
        moves = listOf(scratch, flameThrower),
        image = R.drawable.charader
    ),
    Brogemon(
        name = "Bullsaur",
        type = setOf(Type.TREE),
        level = mutableIntStateOf(default_level),
        experience = mutableIntStateOf(0),
        baseHealth = 20,
        healthPerLevel = 2,
        health = mutableIntStateOf(20 + (default_level * 2)),
        defense = 5,
        speed = 5,
        moves = listOf(scratch, vineWhip),
        image = R.drawable.bullsaur
    ),
    Brogemon(
        name = "Squirle",
        type = setOf(Type.OCEAN),
        level = mutableIntStateOf(default_level),
        experience = mutableIntStateOf(0),
        baseHealth = 20,
        healthPerLevel = 2,
        health = mutableIntStateOf(20 + (default_level * 2)),
        defense = 5,
        speed = 5,
        moves = listOf(scratch, waterGun),
        image = R.drawable.squirle
    )
    // Add other Brogemons here...
)