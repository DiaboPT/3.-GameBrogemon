package com.example.a3gamebrogemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SelectBrogemonMenu(
    onPlayClick: () -> Unit = {}
){
    var ShowStartButton by remember { mutableStateOf(false) }
    var selectedCharacter by remember { mutableStateOf<Brogemon?>(null) }

    val infoText by remember {
        derivedStateOf {
            selectedCharacter?.let { brogemon ->
                val typesText = brogemon.type.joinToString(", ") { it.name }
                "${brogemon.name}: Types - $typesText"
            } ?: "Select a Brogemon"
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Select Your Brogemon", fontSize = 24.sp, color = Color.Black)

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            val scrollState = rememberScrollState()

            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.Start
            ) {
                Brogemons.forEach { brogemon ->
                    CharacterRadioButton(
                        character = brogemon,
                        selectedCharacter = selectedCharacter,
                        onSelect = { selectedCharacter = brogemon }
                    )
                }
            }

            Spacer(modifier = Modifier.width(20.dp))

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = infoText, fontSize = 18.sp)

                Spacer(modifier = Modifier.height(10.dp))

                selectedCharacter?.let { painterResource(id = it.image) }
                    ?.let {
                        Image(
                            painter = it,
                            contentDescription = selectedCharacter?.name,
                            modifier = Modifier.size(150.dp)
                        )
                        ShowStartButton = true
                    }

                Spacer(modifier = Modifier.height(20.dp))

                // Start Game Button
                if(ShowStartButton)
                    Button(onClick = { onPlayClick() }) { }
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
            selected = selectedCharacter?.name == character.name,
            onClick = { onSelect(character) }
        )
        Text(text = character.name, modifier = Modifier.padding(start = 8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun SelectBrogemonMenuPreview(){
    val onPlayClick: () -> Unit = {}
    SelectBrogemonMenu(onPlayClick = onPlayClick)
}