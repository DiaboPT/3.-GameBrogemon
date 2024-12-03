package com.example.a3gamebrogemon

data class Brogemon(
    val name: String,
    val type: Set<Type>,
    val maxHealth: Int,
    val health: Int = maxHealth,
    val defense: Int,
    val speed: Int,
    val moves: List<Move>,
    val image: Int
)

data class Move(
    val name: String,
    val type: Type,
    val power: Int,
    val accuracy: Int
)

enum class Type {
    NORMAL, HOT, TREE, OCEAN, SAND, FREEZE, SHOCK
}

// Define moves
val scratch = Move(name = "Scratch", type = Type.NORMAL, power = 50, accuracy = 95)
val flameThrower = Move(name = "Flame Thrower", type = Type.HOT, power = 90, accuracy = 85)
val vineWhip = Move(name = "Vine Whip", type = Type.TREE, power = 70, accuracy = 90)
val waterGun = Move(name = "Water Gun", type = Type.OCEAN, power = 80, accuracy = 90)

// Define Brogemons
val brogemons = listOf(
    Brogemon(
        name = "Charader",
        type = setOf(Type.HOT),
        maxHealth = 100,
        defense = 30,
        speed = 40,
        moves = listOf(scratch, flameThrower),
        image = R.drawable.charader
    ),
    Brogemon(
        name = "Bullsaur",
        type = setOf(Type.TREE),
        maxHealth = 120,
        defense = 50,
        speed = 35,
        moves = listOf(scratch, vineWhip),
        image = R.drawable.bullsaur
    ),
    Brogemon(
        name = "Squirle",
        type = setOf(Type.OCEAN),
        maxHealth = 110,
        defense = 40,
        speed = 45,
        moves = listOf(scratch, waterGun),
        image = R.drawable.squirle
    )
    // Add other Brogemons here...
)
