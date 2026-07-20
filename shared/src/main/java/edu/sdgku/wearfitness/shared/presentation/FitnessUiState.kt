package edu.sdgku.wearfitness.shared.presentation

import edu.sdgku.wearfitness.shared.model.FitnessData

data class FitnessUiState(
    val fitnessData: FitnessData = FitnessData(),
    val isLoading: Boolean = true,
    val errorMessage: String? = null
)