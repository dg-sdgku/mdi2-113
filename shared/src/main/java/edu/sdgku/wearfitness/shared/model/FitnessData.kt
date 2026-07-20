package edu.sdgku.wearfitness.shared.model

data class FitnessData(
    val dailyGoal: Long = 10_000,
    val steps: Long = 0,
    val heartRate: Long = 72
)