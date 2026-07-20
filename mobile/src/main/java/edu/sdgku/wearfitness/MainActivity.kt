package edu.sdgku.wearfitness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import edu.sdgku.wearfitness.presentation.MobileFitnessApp
import edu.sdgku.wearfitness.ui.theme.WearFitnessTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)

        setContent {
            WearFitnessTheme {
                MobileFitnessApp()
            }
        }
    }
}