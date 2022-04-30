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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.konnov.databasesandroid.ui.theme.DatabasesAndroidTheme
import dev.konnov.feature.realm.ui.RealmScreen
import dev.konnov.feature.sqliteopenhelper.ui.SqliteOpenHelperScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "main") {
                composable("main") {
                    MainScreen(
                        { navController.navigate("sqliteopenhelper") },
                        { navController.navigate("realm") }
                    )
                }

                composable("sqliteopenhelper") { SqliteOpenHelperScreen(hiltViewModel()) }

                composable("realm") { RealmScreen(hiltViewModel()) }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, widthDp = 320, heightDp = 700)
private fun PreviewMainScreen() {
    MainScreen({}, {})
}

@Composable
private fun MainScreen(
    sqliteOpenHelperClicked: () -> Unit,
    realmClicked: () -> Unit
) {
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
                Button(onClick = { sqliteOpenHelperClicked() }) {
                    Text(text = "SqliteOpenHelper")
                }
                Button(onClick = { realmClicked() }) {
                    Text(text = "Realm")
                }
            }
        }
    }
}


// About hilt injection of the view model:
/**
 * According to official documentation https://developer.android.com/jetpack/compose/libraries#hilt-navigation
 *
 * If your @HiltViewModel annotated ViewModel is scoped to
 * the navigation graph, use the hiltViewModel composable function
 * that works with fragments or activities that are annotated with @AndroidEntryPoint.
 */
