package com.calculator.scientific

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.calculator.scientific.data.AppLanguage
import com.calculator.scientific.data.AppTheme
import com.calculator.scientific.navigation.NavGraph
import com.calculator.scientific.ui.theme.ScientificCalculatorTheme
import com.calculator.scientific.viewmodel.CalculatorViewModel
import com.calculator.scientific.viewmodel.SettingsViewModel
import java.util.Locale

class MainActivity : ComponentActivity() {

    private var currentLanguage: AppLanguage? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val settingsViewModel: SettingsViewModel = viewModel()
            val calculatorViewModel: CalculatorViewModel = viewModel()
            val currentTheme by settingsViewModel.theme.collectAsState()
            val language by settingsViewModel.language.collectAsState()

            // Apply locale on language change
            LaunchedEffect(language) {
                if (currentLanguage != language) {
                    currentLanguage = language
                    applyLocale(this@MainActivity, language)
                }
            }

            // Determine dark theme
            val darkTheme = when (currentTheme) {
                AppTheme.DARK -> true
                AppTheme.LIGHT -> false
                AppTheme.SYSTEM -> isSystemInDarkTheme()
            }

            ScientificCalculatorTheme(darkTheme = darkTheme) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavGraph(
                        navController = navController,
                        calculatorViewModel = calculatorViewModel,
                        settingsViewModel = settingsViewModel
                    )
                }
            }
        }
    }

    private fun applyLocale(context: Context, language: AppLanguage) {
        val locale = when (language) {
            AppLanguage.ENGLISH -> Locale("en")
            AppLanguage.INDONESIAN -> Locale("in")
        }
        Locale.setDefault(locale)
        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)
        context.resources.updateConfiguration(config, context.resources.displayMetrics)
    }

    override fun attachBaseContext(newBase: Context) {
        // Apply saved language preference
        val prefs = newBase.getSharedPreferences("settings", Context.MODE_PRIVATE)
        val langCode = prefs.getString("language", "english") ?: "english"
        val locale = when (langCode) {
            "indonesian" -> Locale("in")
            else -> Locale("en")
        }
        val config = Configuration(newBase.resources.configuration)
        config.setLocale(locale)
        val context = newBase.createConfigurationContext(config)
        super.attachBaseContext(context)
    }
}
