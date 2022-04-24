package dev.konnov.databasesandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.konnov.databasesandroid.ui.theme.DatabasesAndroidTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
@Preview(showBackground = true, widthDp = 320, heightDp = 700)
private fun PreviewMainScreen() {
    MainScreen()
}

@Composable
private fun MainScreen() {
    DatabasesAndroidTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(Modifier.fillMaxSize().padding(32.dp)) {
                Text(text = "Choose a db to test")
                Button(onClick = { /*TODO open sql screen*/ }) {
                    Text(text = "SqliteHelper")
                }
            }
        }
    }
}