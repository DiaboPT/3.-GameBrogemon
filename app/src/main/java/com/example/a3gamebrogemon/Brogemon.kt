package com.example.a3gamebrogemon

import com.example.a3gamebrogemon.Enum_Test.*
import java.util.EnumSet

data class Brogemon(
    val name: String,
    val type: EnumSet<Enum_Test>,
    val image: Int
)

val Brogemons = listOf(
    Brogemon(
        name = "Charader",
        type = EnumSet.of(HOT),
        image = R.drawable.charader
    ),
    Brogemon(
        name = "Bullsaur",
        type = EnumSet.of(TREE),
        image = R.drawable.bullsaur
    ),
    Brogemon(
        name = "Squirle",
        type = EnumSet.of(OCEAN),
        image = R.drawable.squirle
    ),
    Brogemon(
        name = "Sandscrew",
        type = EnumSet.of(SAND),
        image = R.drawable.sandshrew
    ),
    Brogemon(
        name = "Beaa-Tchoo",
        type = EnumSet.of(FREEZE),
        image = R.drawable.cubchoo
    ),
    Brogemon(
        name = "Zy-yapper",
        type = EnumSet.of(SHOCK),
        image = R.drawable.yamper
    ),
    Brogemon(
        name = "",
        type = EnumSet.of(HOT, TREE),
        image = R.drawable.charader
    ),
    Brogemon(
        name = "",
        type = EnumSet.of(HOT, OCEAN),
        image = R.drawable.charader
    ),
    Brogemon(
        name = "",
        type = EnumSet.of(HOT, SAND),
        image = R.drawable.charader
    ),
    Brogemon(
        name = "",
        type = EnumSet.of(HOT, FREEZE),
        image = R.drawable.charader
    ),
    Brogemon(
        name = "",
        type = EnumSet.of(HOT, SHOCK),
        image = R.drawable.charader
    ),
    Brogemon(
        name = "",
        type = EnumSet.of(TREE, HOT),
        image = R.drawable.charader
    ),
    Brogemon(
        name = "",
        type = EnumSet.of(TREE, OCEAN),
        image = R.drawable.charader
    ),
    Brogemon(
        name = "",
        type = EnumSet.of(TREE, SAND),
        image = R.drawable.charader
    ),
    Brogemon(
        name = "",
        type = EnumSet.of(TREE, FREEZE),
        image = R.drawable.charader
    ),
    Brogemon(
        name = "",
        type = EnumSet.of(TREE, SHOCK),
        image = R.drawable.charader
    ),
    Brogemon(
        name = "",
        type = EnumSet.of(OCEAN, TREE),
        image = R.drawable.charader
    ),
    Brogemon(
        name = "",
        type = EnumSet.of(OCEAN, SAND),
        image = R.drawable.charader
    ),
    Brogemon(
        name = "",
        type = EnumSet.of(OCEAN, FREEZE),
        image = R.drawable.charader
    ),
    Brogemon(
        name = "",
        type = EnumSet.of(OCEAN, SHOCK),
        image = R.drawable.charader
    ),
    Brogemon(
        name = "",
        type = EnumSet.of(OCEAN, HOT),
        image = R.drawable.charader
    ),
    Brogemon(
        name = "",
        type = EnumSet.of(SAND, TREE),
        image = R.drawable.charader
    ),
    Brogemon(
        name = "",
        type = EnumSet.of(SAND, OCEAN),
        image = R.drawable.charader
    ),
    Brogemon(
        name = "",
        type = EnumSet.of(SAND, FREEZE),
        image = R.drawable.charader
    ),
    Brogemon(
        name = "",
        type = EnumSet.of(SAND, SHOCK),
        image = R.drawable.charader
    ),
    Brogemon(
        name = "",
        type = EnumSet.of(SAND, HOT),
        image = R.drawable.charader
    ),
    Brogemon(
        name = "",
        type = EnumSet.of(FREEZE, TREE),
        image = R.drawable.charader
    ),
    Brogemon(
        name = "",
        type = EnumSet.of(FREEZE, OCEAN),
        image = R.drawable.charader
    ),
    Brogemon(
        name = "",
        type = EnumSet.of(FREEZE, SAND),
        image = R.drawable.charader
    ),
    Brogemon(
        name = "",
        type = EnumSet.of(FREEZE, SHOCK),
        image = R.drawable.charader
    ),
    Brogemon(
        name = "",
        type = EnumSet.of(FREEZE, HOT),
        image = R.drawable.charader
    ),
    Brogemon(
        name = "",
        type = EnumSet.of(SHOCK, TREE),
        image = R.drawable.charader
    ),
    Brogemon(
        name = "",
        type = EnumSet.of(SHOCK, OCEAN),
        image = R.drawable.charader
    ),
    Brogemon(
        name = "",
        type = EnumSet.of(SHOCK, SAND),
        image = R.drawable.charader
    ),
    Brogemon(
        name = "",
        type = EnumSet.of(SHOCK, FREEZE),
        image = R.drawable.charader
    ),
    Brogemon(
        name = "",
        type = EnumSet.of(SHOCK, HOT),
        image = R.drawable.charader
    )
)
