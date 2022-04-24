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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.konnov.databasesandroid.ui.theme.DatabasesAndroidTheme
import dev.konnov.feature.sqlitehelper.SqliteHelperScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "main") {
                composable("main") { MainScreen({ navController.navigate("sqlitehelper") }) }
                composable("sqlitehelper") { SqliteHelperScreen() }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, widthDp = 320, heightDp = 700)
private fun PreviewMainScreen() {
    MainScreen({})
}

@Composable
private fun MainScreen(sqliteHelperClicked: () -> Unit) {
    DatabasesAndroidTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(32.dp)
            ) {
                Text(text = "Choose a db to test")
                Button(onClick = { sqliteHelperClicked() }) {
                    Text(text = "SqliteHelper")
                }
            }
        }
    }
}