package com.calculator.scientific.viewmodel

import androidx.lifecycle.ViewModel
import com.calculator.scientific.engine.CalculatorEngine
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class CalculatorUiState(
    val expression: String = "",
    val result: String = "0",
    val isScientificMode: Boolean = false,
    val isError: Boolean = false,
    val lastResult: String? = null
)

class CalculatorViewModel : ViewModel() {

    private val engine = CalculatorEngine()

    private val _uiState = MutableStateFlow(CalculatorUiState())
    val uiState: StateFlow<CalculatorUiState> = _uiState.asStateFlow()

    // Track parentheses count for balanced checking
    private var openParens = 0

    fun onNumberInput(number: String) {
        val current = _uiState.value
        if (current.isError) {
            _uiState.value = current.copy(
                expression = number,
                result = number,
                isError = false,
                lastResult = null
            )
        } else {
            val newExpr = current.expression + number
            _uiState.value = current.copy(
                expression = newExpr,
                result = tryEvaluate(newExpr),
                lastResult = null
            )
        }
    }

    fun onDecimalInput() {
        val current = _uiState.value
        if (current.isError) {
            _uiState.value = current.copy(
                expression = "0.",
                result = "0.",
                isError = false,
                lastResult = null
            )
            return
        }

        // Find the last number segment
        val lastNumber = extractLastNumber(current.expression)
        if (!lastNumber.contains('.')) {
            val suffix = if (lastNumber.isEmpty() || current.expression.isEmpty()) "0." else "."
            val newExpr = current.expression + suffix
            _uiState.value = current.copy(
                expression = newExpr,
                result = tryEvaluate(newExpr),
                lastResult = null
            )
        }
    }

    fun onOperatorInput(operator: String) {
        val current = _uiState.value
        if (current.isError) return

        // If there's a last result, use it as the start of a new expression
        val baseExpr = if (current.lastResult != null) {
            current.lastResult
        } else {
            current.expression
        }

        if (baseExpr.isEmpty()) {
            if (operator == "-") {
                _uiState.value = current.copy(
                    expression = "-",
                    result = "-",
                    lastResult = null
                )
            }
            return
        }

        val lastChar = baseExpr.last()
        val operators = setOf('+', '−', '×', '÷', '%', '^')

        // Replace last operator if the last char is an operator
        val newExpr = if (lastChar in operators) {
            baseExpr.dropLast(1) + operator
        } else {
            baseExpr + operator
        }

        _uiState.value = current.copy(
            expression = newExpr,
            result = tryEvaluate(newExpr),
            lastResult = null
        )
    }

    fun onEquals() {
        val current = _uiState.value
        if (current.expression.isEmpty() || current.isError) return

        try {
            val result = engine.evaluate(current.expression)
            val formatted = engine.formatResult(result)
            _uiState.value = current.copy(
                expression = formatted,
                result = formatted,
                isError = false,
                lastResult = formatted
            )
        } catch (e: Exception) {
            _uiState.value = current.copy(
                result = "Error",
                isError = true,
                lastResult = null
            )
        }
    }

    fun onClear() {
        openParens = 0
        _uiState.value = CalculatorUiState(isScientificMode = _uiState.value.isScientificMode)
    }

    fun onBackspace() {
        val current = _uiState.value
        if (current.isError || current.expression.isEmpty()) {
            onClear()
            return
        }

        // If we had a result, clear it
        if (current.lastResult != null) {
            _uiState.value = current.copy(
                expression = "",
                result = "0",
                lastResult = null
            )
            return
        }

        val removedChar = current.expression.last()
        if (removedChar == '(') openParens--
        if (removedChar == ')') openParens++

        val newExpr = current.expression.dropLast(1)
        _uiState.value = current.copy(
            expression = newExpr,
            result = if (newExpr.isEmpty()) "0" else tryEvaluate(newExpr),
            lastResult = null
        )
    }

    fun onPercent() {
        val current = _uiState.value
        if (current.isError || current.expression.isEmpty()) return

        try {
            val value = engine.evaluate(current.expression)
            val result = value / 100.0
            val formatted = engine.formatResult(result)
            _uiState.value = current.copy(
                expression = formatted,
                result = formatted,
                lastResult = formatted
            )
        } catch (_: Exception) {}
    }

    fun onPlusMinus() {
        val current = _uiState.value
        if (current.isError) return

        if (current.expression.isEmpty()) {
            _uiState.value = current.copy(expression = "-", result = "-")
            return
        }

        val expr = current.expression
        when {
            expr.startsWith("-") -> {
                val newExpr = expr.removePrefix("-")
                _uiState.value = current.copy(
                    expression = newExpr,
                    result = if (newExpr.isEmpty()) "0" else tryEvaluate(newExpr)
                )
            }
            else -> {
                val newExpr = "-$expr"
                _uiState.value = current.copy(
                    expression = newExpr,
                    result = tryEvaluate(newExpr)
                )
            }
        }
    }

    fun onFunctionInput(function: String) {
        val current = _uiState.value
        val prefix = if (current.isError || (current.lastResult != null)) "" else current.expression

        val funcExpr = when (function) {
            "π" -> {
                if (current.isError) "" else current.expression + "π"
            }
            "e" -> {
                if (current.isError) "" else current.expression + "e"
            }
            "x²" -> {
                if (current.isError || current.expression.isEmpty()) return
                val base = current.lastResult ?: current.expression
                "$base^2"
            }
            "xⁿ" -> {
                if (current.isError || current.expression.isEmpty()) return
                val base = current.lastResult ?: current.expression
                "$base^"
            }
            "n!" -> {
                if (current.isError || current.expression.isEmpty()) return
                val base = current.lastResult ?: current.expression
                "$base!"
            }
            "(" -> {
                openParens++
                prefix + "("
            }
            ")" -> {
                if (openParens <= 0) return
                openParens--
                prefix + ")"
            }
            else -> {
                // sin, cos, tan, log, ln, sqrt
                val newPrefix = if (current.lastResult != null) "" else prefix
                newPrefix + "$function("
            }
        }

        val displayResult = tryEvaluate(funcExpr)
        _uiState.value = current.copy(
            expression = funcExpr,
            result = displayResult,
            isError = false,
            lastResult = null
        )
    }

    fun toggleMode() {
        val current = _uiState.value
        _uiState.value = current.copy(isScientificMode = !current.isScientificMode)
    }

    private fun tryEvaluate(expr: String): String {
        if (expr.isEmpty()) return "0"
        return try {
            val result = engine.evaluate(expr)
            engine.formatResult(result)
        } catch (_: Exception) {
            // Don't show error for partial expressions
            _uiState.value.result
        }
    }

    private fun extractLastNumber(expr: String): String {
        var i = expr.length - 1
        while (i >= 0 && (expr[i].isDigit() || expr[i] == '.')) {
            i--
        }
        return expr.substring(i + 1)
    }
}
