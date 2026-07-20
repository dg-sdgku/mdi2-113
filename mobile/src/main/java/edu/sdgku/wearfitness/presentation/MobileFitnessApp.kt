package edu.sdgku.wearfitness.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.sdgku.wearfitness.shared.presentation.FitnessViewModel

@Composable
fun MobileFitnessApp(
    fitnessViewModel: FitnessViewModel = viewModel()
) {
    val uiState by fitnessViewModel.uiState
        .collectAsStateWithLifecycle()

    FitnessScreen(
        uiState = uiState,
        onIncreaseGoal = {
            fitnessViewModel.increaseDailyGoal()
        },
        onDecreaseGoal = {
            fitnessViewModel.decreaseDailyGoal()
        },
        onAddSteps = {
            fitnessViewModel.addSteps()
        }
    )
}