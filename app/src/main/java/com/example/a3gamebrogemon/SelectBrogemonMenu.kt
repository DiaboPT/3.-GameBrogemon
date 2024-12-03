package com.example.a3gamebrogemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SelectBrogemonMenu(
    onPlayClick: (Brogemon) -> Unit
) {
    var selectedCharacter by remember { mutableStateOf<Brogemon?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Select Your Brogemon", fontSize = 24.sp)

        Row(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                brogemons.forEach { brogemon ->
                    CharacterRadioButton(
                        character = brogemon,
                        selectedCharacter = selectedCharacter,
                        onSelect = { selectedCharacter = brogemon }
                    )
                }
            }

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                selectedCharacter?.let { brogemon ->
                    Text(text = "${brogemon.name} - ${brogemon.type.joinToString(", ")}", fontSize = 18.sp)
                    Image(
                        painter = painterResource(id = brogemon.image),
                        contentDescription = brogemon.name,
                        modifier = Modifier.size(150.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(onClick = { onPlayClick(brogemon) }) {
                        Text("Start Battle")
                    }
                }
            }
        }
    }
}

@Composable
fun CharacterRadioButton(
    character: Brogemon,
    selectedCharacter: Brogemon?,
    onSelect: (Brogemon) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(
            selected = selectedCharacter == character,
            onClick = { onSelect(character) }
        )
        Text(text = character.name, modifier = Modifier.padding(start = 8.dp))
    }
}
