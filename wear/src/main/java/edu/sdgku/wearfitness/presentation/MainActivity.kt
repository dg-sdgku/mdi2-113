package edu.sdgku.wearfitness.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import edu.sdgku.wearfitness.presentation.theme.WearFitnessTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WearFitnessTheme {
                WearFitnessApp()
            }
        }
    }
}