// BrogemonSelectionView.kt
package com.example.a3gamebrogemon.views

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.a3gamebrogemon.classes.brogemons
import com.example.a3gamebrogemon.views.models.BrogemonSelectionScreen

class BrogemonSelectionView : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BrogemonSelectionScreen { selectedBrogemonName ->
                // Find the Brogemon object by its name
                val selectedBrogemon = brogemons.find { it.name == selectedBrogemonName.name }
                selectedBrogemon?.let {
                    val intent = Intent(this, BattleMenuView::class.java)
                    intent.putExtra("selectedBrogemon", it) // Brogemon is now Parcelable
                    startActivity(intent)
                }
            }
        }
    }
}
