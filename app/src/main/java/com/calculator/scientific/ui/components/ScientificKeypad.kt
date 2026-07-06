package com.calculator.scientific.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.calculator.scientific.R

@Composable
fun ScientificKeypad(
    onNumber: (String) -> Unit,
    onOperator: (String) -> Unit,
    onEquals: () -> Unit,
    onClear: () -> Unit,
    onBackspace: () -> Unit,
    onPercent: () -> Unit,
    onPlusMinus: () -> Unit,
    onDecimal: () -> Unit,
    onFunction: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        // Scientific functions row 1: sin, cos, tan, (, )
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.weight(1f)
        ) {
            CalculatorButton(
                text = stringResource(R.string.btn_sin),
                onClick = { onFunction("sin") },
                buttonType = ButtonType.FUNCTION,
                isCompact = true,
                modifier = Modifier.weight(1f)
            )
            CalculatorButton(
                text = stringResource(R.string.btn_cos),
                onClick = { onFunction("cos") },
                buttonType = ButtonType.FUNCTION,
                isCompact = true,
                modifier = Modifier.weight(1f)
            )
            CalculatorButton(
                text = stringResource(R.string.btn_tan),
                onClick = { onFunction("tan") },
                buttonType = ButtonType.FUNCTION,
                isCompact = true,
                modifier = Modifier.weight(1f)
            )
            CalculatorButton(
                text = stringResource(R.string.btn_open_paren),
                onClick = { onFunction("(") },
                buttonType = ButtonType.FUNCTION,
                isCompact = true,
                modifier = Modifier.weight(1f)
            )
            CalculatorButton(
                text = stringResource(R.string.btn_close_paren),
                onClick = { onFunction(")") },
                buttonType = ButtonType.FUNCTION,
                isCompact = true,
                modifier = Modifier.weight(1f)
            )
        }

        // Scientific functions row 2: log, ln, √, x², xⁿ
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.weight(1f)
        ) {
            CalculatorButton(
                text = stringResource(R.string.btn_log),
                onClick = { onFunction("log") },
                buttonType = ButtonType.FUNCTION,
                isCompact = true,
                modifier = Modifier.weight(1f)
            )
            CalculatorButton(
                text = stringResource(R.string.btn_ln),
                onClick = { onFunction("ln") },
                buttonType = ButtonType.FUNCTION,
                isCompact = true,
                modifier = Modifier.weight(1f)
            )
            CalculatorButton(
                text = stringResource(R.string.btn_sqrt),
                onClick = { onFunction("sqrt") },
                buttonType = ButtonType.FUNCTION,
                isCompact = true,
                modifier = Modifier.weight(1f)
            )
            CalculatorButton(
                text = stringResource(R.string.btn_square),
                onClick = { onFunction("x²") },
                buttonType = ButtonType.FUNCTION,
                isCompact = true,
                modifier = Modifier.weight(1f)
            )
            CalculatorButton(
                text = stringResource(R.string.btn_power),
                onClick = { onFunction("xⁿ") },
                buttonType = ButtonType.FUNCTION,
                isCompact = true,
                modifier = Modifier.weight(1f)
            )
        }

        // Scientific functions row 3: π, e, n!, AC, ⌫
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.weight(1f)
        ) {
            CalculatorButton(
                text = stringResource(R.string.btn_pi),
                onClick = { onFunction("π") },
                buttonType = ButtonType.FUNCTION,
                isCompact = true,
                modifier = Modifier.weight(1f)
            )
            CalculatorButton(
                text = stringResource(R.string.btn_e),
                onClick = { onFunction("e") },
                buttonType = ButtonType.FUNCTION,
                isCompact = true,
                modifier = Modifier.weight(1f)
            )
            CalculatorButton(
                text = stringResource(R.string.btn_factorial),
                onClick = { onFunction("n!") },
                buttonType = ButtonType.FUNCTION,
                isCompact = true,
                modifier = Modifier.weight(1f)
            )
            CalculatorButton(
                text = stringResource(R.string.btn_clear),
                onClick = onClear,
                buttonType = ButtonType.CLEAR,
                isCompact = true,
                modifier = Modifier.weight(1f)
            )
            CalculatorButton(
                text = stringResource(R.string.btn_backspace),
                onClick = onBackspace,
                buttonType = ButtonType.BACKSPACE,
                isCompact = true,
                modifier = Modifier.weight(1f)
            )
        }

        // Row 4: 7, 8, 9, ÷, %
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.weight(1f)
        ) {
            CalculatorButton(text = "7", onClick = { onNumber("7") }, isCompact = true, modifier = Modifier.weight(1f))
            CalculatorButton(text = "8", onClick = { onNumber("8") }, isCompact = true, modifier = Modifier.weight(1f))
            CalculatorButton(text = "9", onClick = { onNumber("9") }, isCompact = true, modifier = Modifier.weight(1f))
            CalculatorButton(
                text = stringResource(R.string.btn_divide),
                onClick = { onOperator("÷") },
                buttonType = ButtonType.OPERATOR,
                isCompact = true,
                modifier = Modifier.weight(1f)
            )
            CalculatorButton(
                text = stringResource(R.string.btn_percent),
                onClick = onPercent,
                buttonType = ButtonType.FUNCTION,
                isCompact = true,
                modifier = Modifier.weight(1f)
            )
        }

        // Row 5: 4, 5, 6, ×, ±
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.weight(1f)
        ) {
            CalculatorButton(text = "4", onClick = { onNumber("4") }, isCompact = true, modifier = Modifier.weight(1f))
            CalculatorButton(text = "5", onClick = { onNumber("5") }, isCompact = true, modifier = Modifier.weight(1f))
            CalculatorButton(text = "6", onClick = { onNumber("6") }, isCompact = true, modifier = Modifier.weight(1f))
            CalculatorButton(
                text = stringResource(R.string.btn_multiply),
                onClick = { onOperator("×") },
                buttonType = ButtonType.OPERATOR,
                isCompact = true,
                modifier = Modifier.weight(1f)
            )
            CalculatorButton(
                text = stringResource(R.string.btn_plus_minus),
                onClick = onPlusMinus,
                buttonType = ButtonType.FUNCTION,
                isCompact = true,
                modifier = Modifier.weight(1f)
            )
        }

        // Row 6: 1, 2, 3, −, =
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.weight(1f)
        ) {
            CalculatorButton(text = "1", onClick = { onNumber("1") }, isCompact = true, modifier = Modifier.weight(1f))
            CalculatorButton(text = "2", onClick = { onNumber("2") }, isCompact = true, modifier = Modifier.weight(1f))
            CalculatorButton(text = "3", onClick = { onNumber("3") }, isCompact = true, modifier = Modifier.weight(1f))
            CalculatorButton(
                text = stringResource(R.string.btn_subtract),
                onClick = { onOperator("−") },
                buttonType = ButtonType.OPERATOR,
                isCompact = true,
                modifier = Modifier.weight(1f)
            )
            CalculatorButton(
                text = stringResource(R.string.btn_equals),
                onClick = onEquals,
                buttonType = ButtonType.EQUALS,
                isCompact = true,
                modifier = Modifier.weight(1f)
            )
        }

        // Row 7: 0 (wide), ., +
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.weight(1f)
        ) {
            CalculatorButton(text = "0", onClick = { onNumber("0") }, isCompact = true, modifier = Modifier.weight(2f).padding(end = 0.dp))
            CalculatorButton(
                text = stringResource(R.string.btn_decimal),
                onClick = onDecimal,
                isCompact = true,
                modifier = Modifier.weight(1f)
            )
            CalculatorButton(
                text = stringResource(R.string.btn_add),
                onClick = { onOperator("+") },
                buttonType = ButtonType.OPERATOR,
                isCompact = true,
                modifier = Modifier.weight(2f)
            )
        }
    }
}
