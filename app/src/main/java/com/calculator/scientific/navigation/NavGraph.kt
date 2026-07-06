package com.calculator.scientific.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.calculator.scientific.ui.screens.MainScreen
import com.calculator.scientific.ui.screens.SettingsScreen
import com.calculator.scientific.viewmodel.CalculatorViewModel
import com.calculator.scientific.viewmodel.SettingsViewModel

object Routes {
    const val MAIN = "main"
    const val SETTINGS = "settings"
}

@Composable
fun NavGraph(
    navController: NavHostController,
    calculatorViewModel: CalculatorViewModel,
    settingsViewModel: SettingsViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Routes.MAIN,
        modifier = modifier
    ) {
        composable(Routes.MAIN) {
            MainScreen(
                viewModel = calculatorViewModel,
                onNavigateToSettings = {
                    navController.navigate(Routes.SETTINGS)
                }
            )
        }

        composable(Routes.SETTINGS) {
            SettingsScreen(
                settingsViewModel = settingsViewModel,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
