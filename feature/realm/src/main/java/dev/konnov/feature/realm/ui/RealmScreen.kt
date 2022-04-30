package dev.konnov.feature.realm.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.konnov.feature.realm.presentation.RealmViewModel

@Preview(showBackground = true, widthDp = 320, heightDp = 700)
@Composable
private fun PreviewRealmScreen() {
    RealmMainScreen()
}

@Composable
fun RealmScreen(
    viewModel: RealmViewModel
) {
    viewModel.testDbSpeed()
    RealmMainScreen()
}

@Composable
private fun RealmMainScreen() {
    Text("Hello realm")
}