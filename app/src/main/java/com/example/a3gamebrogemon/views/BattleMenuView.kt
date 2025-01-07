// BattleMenuView.kt
package com.example.a3gamebrogemon.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.a3gamebrogemon.classes.Brogemon
import com.example.a3gamebrogemon.views.models.BattleMenuScreen

class BattleMenuView : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val selectedBrogemon = intent.getParcelableExtra<Brogemon>("selectedBrogemon")
        setContent {
            selectedBrogemon?.let {
                BattleMenuScreen(it)
            }
        }
    }
}
