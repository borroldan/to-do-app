package com.example.to_doapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.to_doapp.ui.theme.ToDoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val toDoViewModel = ViewModelProvider(this)[ToDoViewModel::class.java]
        enableEdgeToEdge()
        setContent {
            ToDoAppTheme {
                val navController = rememberNavController()
                Surface (
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier.fillMaxSize()
                ) {
                    NavHost(navController = navController, startDestination = "todo_list") {
                        composable("todo_list") {
                            SwipeMenu(navController) {
                                ToDoListPage(toDoViewModel)
                            }
                        }
                        composable("calendar") {
                            SwipeMenu(navController) {
                                CalendarPage()
                            }
                        }
                        composable("settings") {
                            SwipeMenu(navController) {
                                SettingsPage()
                            }
                        }
                    }
                }
            }
        }
    }
}
