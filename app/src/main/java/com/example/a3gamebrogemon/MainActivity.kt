// MainActivity.kt
package com.example.a3gamebrogemon

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import com.example.a3gamebrogemon.views.BrogemonSelectionView
import com.example.a3gamebrogemon.views.models.MainMenu
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.auth

const val TAG = "ShoppingList"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this) // Initializes Firebase
        setContent {
        MainMenu { navigateTo ->
                when (navigateTo) {
                    "selectBrogemon" -> startActivity(
                        Intent(this, BrogemonSelectionView::class.java)
                    )
                }
            }
            LaunchedEffect(Unit) {
                val auth = Firebase.auth
                val currentUser = auth.currentUser
                print(currentUser)
            }
        }

    }
}
