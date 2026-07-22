package edu.sdgku.wearfitness.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import com.google.android.gms.wearable.Wearable
import edu.sdgku.wearfitness.presentation.theme.WearFitnessTheme
import edu.sdgku.wearfitness.shared.data.FirebaseRepository


class MainActivity : ComponentActivity() {
    private var heartRate by mutableIntStateOf(72)
    private var stepsGoal by mutableIntStateOf(10000)
    private lateinit var wearDataListener: WearDataListener
    private lateinit var heartRateSensorManager: HeartRateSensorManager
    private val heartRatePermissionLauncher = registerForActivityResult(ActivityResultContracts
        .RequestPermission()) { isGranted ->
        if(isGranted) {
            heartRateSensorManager.startListening()
        }
    }

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)
        val repository = FirebaseRepository()

        repository.listenToFitnessData(
            onDataChanged = {fitnessData ->
                Log.d("SharedFirebaseWear","Goal received: ${fitnessData.dailyGoal}")
            })
        createNotificationChannel(this)
        heartRateSensorManager = HeartRateSensorManager(context = this, onHeartRateChanged = {
                        newHeartRate -> heartRate = newHeartRate
                }
            )
        if (heartRateSensorManager.hasHeartRateSensor && !heartRateSensorManager.hasPermission()
        ) {
            heartRatePermissionLauncher.launch(heartRateSensorManager.requiredPermission)
        }

        wearDataListener = WearDataListener(onStepsGoalChanged = {newGoal ->
            runOnUiThread { stepsGoal = newGoal }
        })

        setContent {
            WearFitnessTheme {
                WearFitnessApp(
                    heartRateSensorValue = heartRate,
                    hasHeartRateSensor = heartRateSensorManager.hasHeartRateSensor,
                    stepsGoalFromPhone = stepsGoal
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (::heartRateSensorManager.isInitialized) {
            heartRateSensorManager.startListening()
        }
        if(::wearDataListener.isInitialized) {
            Wearable.getDataClient(this).addListener ( wearDataListener )
        }
    }

    override fun onPause() {
        super.onPause()
        if (::heartRateSensorManager.isInitialized) {
            heartRateSensorManager.stopListening()
        }
        if(::wearDataListener.isInitialized) {
            Wearable.getDataClient(this).removeListener ( wearDataListener )
        }
    }
}