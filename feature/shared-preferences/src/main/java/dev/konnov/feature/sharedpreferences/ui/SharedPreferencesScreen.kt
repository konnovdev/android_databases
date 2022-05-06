package dev.konnov.feature.sharedpreferences.di

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.konnov.feature.sharedpreferences.presentation.SharedPreferencesViewModel

@Preview(showBackground = true, widthDp = 320, heightDp = 700)
@Composable
private fun PreviewSharedPreferencesScreen() {
    SharedPreferenceMainScreen()
}

@Composable
fun SharedPreferenceScreen(
    viewModel: SharedPreferencesViewModel
) {
    viewModel.testDbSpeed() // TODO do in background
    SharedPreferenceMainScreen()
}

@Composable
private fun SharedPreferenceMainScreen() {
    Text("Hello SharedPreferences")
}