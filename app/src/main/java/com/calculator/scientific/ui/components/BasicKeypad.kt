package com.calculator.scientific.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.calculator.scientific.R

@Composable
fun BasicKeypad(
    onNumber: (String) -> Unit,
    onOperator: (String) -> Unit,
    onEquals: () -> Unit,
    onClear: () -> Unit,
    onBackspace: () -> Unit,
    onPercent: () -> Unit,
    onPlusMinus: () -> Unit,
    onDecimal: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Row 1: AC, ⌫, %, ÷
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            CalculatorButton(
                text = stringResource(R.string.btn_clear),
                onClick = onClear,
                buttonType = ButtonType.CLEAR,
                modifier = Modifier.weight(1f)
            )
            CalculatorButton(
                text = stringResource(R.string.btn_backspace),
                onClick = onBackspace,
                buttonType = ButtonType.BACKSPACE,
                modifier = Modifier.weight(1f)
            )
            CalculatorButton(
                text = stringResource(R.string.btn_percent),
                onClick = onPercent,
                buttonType = ButtonType.FUNCTION,
                modifier = Modifier.weight(1f)
            )
            CalculatorButton(
                text = stringResource(R.string.btn_divide),
                onClick = { onOperator("÷") },
                buttonType = ButtonType.OPERATOR,
                modifier = Modifier.weight(1f)
            )
        }

        // Row 2: 7, 8, 9, ×
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            CalculatorButton(text = "7", onClick = { onNumber("7") }, modifier = Modifier.weight(1f))
            CalculatorButton(text = "8", onClick = { onNumber("8") }, modifier = Modifier.weight(1f))
            CalculatorButton(text = "9", onClick = { onNumber("9") }, modifier = Modifier.weight(1f))
            CalculatorButton(
                text = stringResource(R.string.btn_multiply),
                onClick = { onOperator("×") },
                buttonType = ButtonType.OPERATOR,
                modifier = Modifier.weight(1f)
            )
        }

        // Row 3: 4, 5, 6, −
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            CalculatorButton(text = "4", onClick = { onNumber("4") }, modifier = Modifier.weight(1f))
            CalculatorButton(text = "5", onClick = { onNumber("5") }, modifier = Modifier.weight(1f))
            CalculatorButton(text = "6", onClick = { onNumber("6") }, modifier = Modifier.weight(1f))
            CalculatorButton(
                text = stringResource(R.string.btn_subtract),
                onClick = { onOperator("−") },
                buttonType = ButtonType.OPERATOR,
                modifier = Modifier.weight(1f)
            )
        }

        // Row 4: 1, 2, 3, +
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            CalculatorButton(text = "1", onClick = { onNumber("1") }, modifier = Modifier.weight(1f))
            CalculatorButton(text = "2", onClick = { onNumber("2") }, modifier = Modifier.weight(1f))
            CalculatorButton(text = "3", onClick = { onNumber("3") }, modifier = Modifier.weight(1f))
            CalculatorButton(
                text = stringResource(R.string.btn_add),
                onClick = { onOperator("+") },
                buttonType = ButtonType.OPERATOR,
                modifier = Modifier.weight(1f)
            )
        }

        // Row 5: ±, 0, ., =
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            CalculatorButton(
                text = stringResource(R.string.btn_plus_minus),
                onClick = onPlusMinus,
                buttonType = ButtonType.FUNCTION,
                modifier = Modifier.weight(1f)
            )
            CalculatorButton(text = "0", onClick = { onNumber("0") }, modifier = Modifier.weight(1f))
            CalculatorButton(
                text = stringResource(R.string.btn_decimal),
                onClick = onDecimal,
                modifier = Modifier.weight(1f)
            )
            CalculatorButton(
                text = stringResource(R.string.btn_equals),
                onClick = onEquals,
                buttonType = ButtonType.EQUALS,
                modifier = Modifier.weight(1f)
            )
        }
    }
}
