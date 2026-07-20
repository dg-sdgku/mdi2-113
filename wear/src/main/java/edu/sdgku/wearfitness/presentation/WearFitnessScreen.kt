package edu.sdgku.wearfitness.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material3.Button
import androidx.wear.compose.material3.MaterialTheme
import androidx.wear.compose.material3.Text
import edu.sdgku.wearfitness.shared.presentation.FitnessUiState

@Composable
fun WearFitnessScreen(
    uiState: FitnessUiState,
    onIncreaseGoal: () -> Unit,
    onDecreaseGoal: () -> Unit,
    onAddSteps: () -> Unit
) {
    if (uiState.isLoading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Loading..."
            )
        }

        return
    }

    val fitnessData = uiState.fitnessData

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "WearFitness",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "${fitnessData.steps} steps",
            style = MaterialTheme.typography.titleLarge
        )

        Button(
            onClick = onAddSteps
        ) {
            Text("+100")
        }

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "Goal: ${fitnessData.dailyGoal}"
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = onDecreaseGoal
            ) {
                Text("-")
            }

            Button(
                onClick = onIncreaseGoal
            ) {
                Text("+")
            }
        }

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "${fitnessData.heartRate} BPM"
        )

        uiState.errorMessage?.let { errorMessage ->
            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = errorMessage
            )
        }
    }
}