package com.calculator.scientific.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.calculator.scientific.R
import com.calculator.scientific.ui.components.*
import com.calculator.scientific.viewmodel.CalculatorViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: CalculatorViewModel,
    onNavigateToSettings: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                actions = {
                    IconButton(onClick = onNavigateToSettings) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = stringResource(R.string.settings)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Display Panel
            DisplayPanel(
                expression = uiState.expression,
                result = uiState.result,
                isError = uiState.isError,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 120.dp)
            )

            // Mode Toggle
            ModeToggle(
                isScientificMode = uiState.isScientificMode,
                onToggle = { viewModel.toggleMode() },
                basicLabel = stringResource(R.string.basic_mode),
                scientificLabel = stringResource(R.string.scientific_mode),
                modifier = Modifier.fillMaxWidth()
            )

            // Keypad
            if (uiState.isScientificMode) {
                ScientificKeypad(
                    onNumber = { viewModel.onNumberInput(it) },
                    onOperator = { viewModel.onOperatorInput(it) },
                    onEquals = { viewModel.onEquals() },
                    onClear = { viewModel.onClear() },
                    onBackspace = { viewModel.onBackspace() },
                    onPercent = { viewModel.onPercent() },
                    onPlusMinus = { viewModel.onPlusMinus() },
                    onDecimal = { viewModel.onDecimalInput() },
                    onFunction = { viewModel.onFunctionInput(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
            } else {
                BasicKeypad(
                    onNumber = { viewModel.onNumberInput(it) },
                    onOperator = { viewModel.onOperatorInput(it) },
                    onEquals = { viewModel.onEquals() },
                    onClear = { viewModel.onClear() },
                    onBackspace = { viewModel.onBackspace() },
                    onPercent = { viewModel.onPercent() },
                    onPlusMinus = { viewModel.onPlusMinus() },
                    onDecimal = { viewModel.onDecimalInput() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
            }
        }
    }
}
