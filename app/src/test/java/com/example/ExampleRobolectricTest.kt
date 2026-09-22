package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("Hydratation", appName)
  }

  @Test
  fun `verify hydration view model initial state`() {
    val viewModel = HydrationViewModel()
    val state = viewModel.uiState.value
    assertEquals(0, state.currentIntakeMl)
    assertEquals(2000, state.goalMl)
    assertEquals(0f, state.progress)
  }

  @Test
  fun `verify add water 250ml increments intake`() {
    val viewModel = HydrationViewModel()
    viewModel.addWater(250)
    val state = viewModel.uiState.value
    assertEquals(250, state.currentIntakeMl)
    assertEquals(1, state.history.size)
    assertEquals(250, state.history.first().amountMl)
  }

  @Test
  fun `verify reset clears intake`() {
    val viewModel = HydrationViewModel()
    viewModel.addWater(250)
    viewModel.addWater(500)
    viewModel.resetWater()
    val state = viewModel.uiState.value
    assertEquals(0, state.currentIntakeMl)
    assertEquals(0, state.history.size)
  }
}

