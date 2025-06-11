package com.example.androiduiexperiments.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.androiduiexperiments.HomeScreen
import com.example.androiduiexperiments.ui.screens.AccessibilityPermissionScreen
import com.example.androiduiexperiments.ui.screens.FireBaseConnectScreen


sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Details : Screen("details/{userId}") {
        fun createRoute(userId: String) = "details/$userId"
    }
}

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = "accessibility_permission") {
        composable("accessibility_permission") { AccessibilityPermissionScreen(navController = navController) }
        composable("firebase_connection/{userId}", arguments = listOf(navArgument("userId") { type = NavType.StringType })) {
            val userId = it.arguments?.getString("userId")
            FireBaseConnectScreen(navController, userId)
        }
    }
}


