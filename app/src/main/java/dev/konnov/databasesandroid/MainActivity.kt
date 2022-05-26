package dev.konnov.databasesandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.konnov.databasesandroid.ui.theme.DatabasesAndroidTheme
import dev.konnov.feature.datastore.ui.DataStoreScreen
import dev.konnov.feature.objectbox.ui.ObjectBoxScreen
import dev.konnov.feature.realm.ui.RealmScreen
import dev.konnov.feature.room.ui.RoomScreen
import dev.konnov.feature.sharedpreferences.ui.SharedPreferenceScreen
import dev.konnov.feature.sqldelight.ui.SqlDelightScreen
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
                        { navController.navigate("realm") },
                        { navController.navigate("room") },
                        { navController.navigate("objectbox") },
                        { navController.navigate("sqldelight") },
                        { navController.navigate("sharedpreferences") },
                        { navController.navigate("datastore") }
                    )
                }

                composable("sqliteopenhelper") { SqliteOpenHelperScreen(hiltViewModel()) }

                composable("realm") { RealmScreen(hiltViewModel()) }

                composable("room") { RoomScreen(hiltViewModel()) }

                composable("objectbox") { ObjectBoxScreen(hiltViewModel()) }

                composable("sqldelight") { SqlDelightScreen(hiltViewModel()) }

                composable("sharedpreferences") { SharedPreferenceScreen(hiltViewModel()) }

                composable("datastore") { DataStoreScreen(hiltViewModel()) }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, widthDp = 320, heightDp = 700)
private fun PreviewMainScreen() {
    MainScreen({}, {}, {}, {}, {}, {}, {})
}

@Composable
private fun MainScreen(
    sqliteOpenHelperClicked: () -> Unit,
    realmClicked: () -> Unit,
    roomClicked: () -> Unit,
    objectBoxClicked: () -> Unit,
    sqlDelightClicked: () -> Unit,
    sharedPrefencesClicked: () -> Unit,
    datastoreClicked: () -> Unit
) {
    DatabasesAndroidTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Choose a db to test performace:",
                    color = Color.Black,
                    style = Typography().h6,
                    modifier = Modifier.padding(bottom = 8.dp))
                OutlinedButton(onClick = { sqliteOpenHelperClicked() }, Modifier.width(200.dp)) {
                    Text(text = "SqliteOpenHelper")
                }
                OutlinedButton(onClick = { realmClicked() }, Modifier.width(200.dp)) {
                    Text(text = "Realm")
                }
                OutlinedButton(onClick = { roomClicked() }, Modifier.width(200.dp)) {
                    Text(text = "Room")
                }
                OutlinedButton(onClick = { objectBoxClicked() }, Modifier.width(200.dp)) {
                    Text(text = "ObjectBox")
                }
                OutlinedButton(onClick = { sqlDelightClicked() }, Modifier.width(200.dp)) {
                    Text(text = "SqlDelight")
                }
                OutlinedButton(onClick = { sharedPrefencesClicked() }, Modifier.width(200.dp)) {
                    Text(text = "sharedPreferences")
                }
                OutlinedButton(onClick = { datastoreClicked() }, Modifier.width(200.dp)) {
                    Text(text = "datastore")
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
