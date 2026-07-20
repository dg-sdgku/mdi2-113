package edu.sdgku.wearfitness.shared.presentation

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ListenerRegistration
import edu.sdgku.wearfitness.shared.data.FirebaseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FitnessViewModel(
    private val repository: FirebaseRepository =
        FirebaseRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        FitnessUiState()
    )

    val uiState: StateFlow<FitnessUiState> =
        _uiState.asStateFlow()

    private var fitnessListener: ListenerRegistration? = null

    init {
        startListening()
    }

    private fun startListening() {
        fitnessListener = repository.listenToFitnessData(
            onDataChanged = { fitnessData ->
                _uiState.value = FitnessUiState(
                    fitnessData = fitnessData,
                    isLoading = false,
                    errorMessage = null
                )
            },
            onError = { exception ->
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = exception.message
                )
            }
        )
    }

    fun increaseDailyGoal() {
        val currentGoal =
            _uiState.value.fitnessData.dailyGoal

        updateDailyGoal(
            newGoal = currentGoal + 1_000
        )
    }

    fun decreaseDailyGoal() {
        val currentGoal =
            _uiState.value.fitnessData.dailyGoal

        val newGoal = (currentGoal - 1_000)
            .coerceAtLeast(1_000)

        updateDailyGoal(
            newGoal = newGoal
        )
    }

    private fun updateDailyGoal(
        newGoal: Long
    ) {
        repository.updateDailyGoal(
            dailyGoal = newGoal,
            onError = { exception ->
                _uiState.value = _uiState.value.copy(
                    errorMessage = exception.message
                )
            }
        )
    }

    fun addSteps(
        amount: Long = 100
    ) {
        val currentSteps =
            _uiState.value.fitnessData.steps

        repository.updateSteps(
            steps = currentSteps + amount,
            onError = { exception ->
                _uiState.value = _uiState.value.copy(
                    errorMessage = exception.message
                )
            }
        )
    }

    fun updateHeartRate(
        heartRate: Long
    ) {
        repository.updateHeartRate(
            heartRate = heartRate,
            onError = { exception ->
                _uiState.value = _uiState.value.copy(
                    errorMessage = exception.message
                )
            }
        )
    }

    override fun onCleared() {
        fitnessListener?.remove()
        fitnessListener = null

        super.onCleared()
    }
}