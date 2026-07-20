package edu.sdgku.wearfitness.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import edu.sdgku.wearfitness.shared.presentation.FitnessUiState

@Composable
fun FitnessScreen(
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
            CircularProgressIndicator()

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Text(
                text = "Loading fitness data..."
            )
        }

        return
    }

    val fitnessData = uiState.fitnessData

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "WearFitness",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White
        )

        Spacer(
            modifier = Modifier.height(32.dp)
        )

        Text(
            text = "Steps",
            style = MaterialTheme.typography.titleMedium,
            color = Color.White
        )

        Text(
            text = "${fitnessData.steps}",
            style = MaterialTheme.typography.displayMedium,
            color = Color.White
        )

        Button(
            onClick = onAddSteps
        ) {
            Text(
                text = "+100 steps"
            )
        }

        Spacer(
            modifier = Modifier.height(32.dp)
        )

        Text(
            text = "Daily Goal",
            style = MaterialTheme.typography.titleMedium,
            color = Color.White
        )

        Text(
            text = "${fitnessData.dailyGoal}",
            style = MaterialTheme.typography.headlineLarge,
            color = Color.White
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Row(
            horizontalArrangement =
                Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = onDecreaseGoal
            ) {
                Text("-1,000")
            }

            Button(
                onClick = onIncreaseGoal
            ) {
                Text("+1,000")
            }
        }

        Spacer(
            modifier = Modifier.height(32.dp)
        )

        Text(
            text = "Heart Rate",
            style = MaterialTheme.typography.titleMedium,
            color = Color.White
        )

        Text(
            text = "${fitnessData.heartRate} BPM",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White
        )

        uiState.errorMessage?.let { errorMessage ->
            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}