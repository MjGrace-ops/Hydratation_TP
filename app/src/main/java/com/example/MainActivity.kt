package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ui.HydrationScreen
import com.example.ui.theme.HydrationTheme

class MainActivity : ComponentActivity() {

  private val hydrationViewModel: HydrationViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      HydrationTheme {
        val state by hydrationViewModel.uiState.collectAsStateWithLifecycle()
        HydrationScreen(
          viewModel = hydrationViewModel,
          state = state
        )
      }
    }
  }
}

@Preview(showBackground = true, backgroundColor = 0xFF0B1317)
@Composable
fun HydrationScreenPreview() {
  HydrationTheme {
    val dummyViewModel = HydrationViewModel()
    val state = HydrationUiState(
      currentIntakeMl = 750,
      goalMl = 2000
    )
    HydrationScreen(
      viewModel = dummyViewModel,
      state = state
    )
  }
}
