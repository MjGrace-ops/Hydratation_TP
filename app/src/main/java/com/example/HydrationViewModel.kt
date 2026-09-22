package com.example

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class IntakeEntry(
    val id: Long = System.currentTimeMillis(),
    val amountMl: Int,
    val timeString: String
)

data class HydrationUiState(
    val currentIntakeMl: Int = 0,
    val goalMl: Int = 2000, // 2 Liters daily goal
    val history: List<IntakeEntry> = emptyList(),
    val showResetDialog: Boolean = false,
    val isGoalJustAchieved: Boolean = false
) {
    val progress: Float
        get() = (currentIntakeMl.toFloat() / goalMl.toFloat()).coerceIn(0f, 1.5f)

    val percentage: Int
        get() = ((currentIntakeMl.toFloat() / goalMl.toFloat()) * 100).toInt()

    val remainingMl: Int
        get() = (goalMl - currentIntakeMl).coerceAtLeast(0)

    val isGoalAchieved: Boolean
        get() = currentIntakeMl >= goalMl

    val currentInLiters: Float
        get() = currentIntakeMl / 1000f

    val goalInLiters: Float
        get() = goalMl / 1000f

    val glassesCount: Int
        get() = currentIntakeMl / 250

    val totalGlassesGoal: Int
        get() = goalMl / 250 // 8 glasses of 250ml
}

class HydrationViewModel : ViewModel() {

    private val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

    private val _uiState = MutableStateFlow(HydrationUiState())
    val uiState: StateFlow<HydrationUiState> = _uiState.asStateFlow()

    fun addWater(amountMl: Int = 250) {
        val currentTime = timeFormat.format(Date())
        val newEntry = IntakeEntry(
            id = System.nanoTime(),
            amountMl = amountMl,
            timeString = currentTime
        )

        _uiState.update { state ->
            val prevIntake = state.currentIntakeMl
            val newIntake = prevIntake + amountMl
            val justAchieved = prevIntake < state.goalMl && newIntake >= state.goalMl

            state.copy(
                currentIntakeMl = newIntake,
                history = listOf(newEntry) + state.history,
                isGoalJustAchieved = justAchieved
            )
        }
    }

    fun undoLast() {
        _uiState.update { state ->
            if (state.history.isEmpty()) return@update state
            val lastEntry = state.history.first()
            val newIntake = (state.currentIntakeMl - lastEntry.amountMl).coerceAtLeast(0)
            state.copy(
                currentIntakeMl = newIntake,
                history = state.history.drop(1),
                isGoalJustAchieved = false
            )
        }
    }

    fun requestReset() {
        _uiState.update { it.copy(showResetDialog = true) }
    }

    fun dismissResetDialog() {
        _uiState.update { it.copy(showResetDialog = false) }
    }

    fun resetWater() {
        _uiState.update {
            it.copy(
                currentIntakeMl = 0,
                history = emptyList(),
                showResetDialog = false,
                isGoalJustAchieved = false
            )
        }
    }
}
