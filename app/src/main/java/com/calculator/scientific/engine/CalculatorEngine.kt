package com.calculator.scientific.engine

import kotlin.math.*

/**
 * CalculatorEngine uses recursive descent parsing to evaluate mathematical expressions.
 * Supports: +, -, *, /, %, ^, !, sin, cos, tan, log, ln, sqrt, pi, e
 */
class CalculatorEngine {

    private var expression = ""
    private var pos = 0

    /**
     * Evaluate a mathematical expression string.
     * Returns the result as a Double, or throws ArithmeticException/NumberFormatException on error.
     */
    fun evaluate(expr: String): Double {
        // Replace display symbols with parser symbols
        expression = expr
            .replace("×", "*")
            .replace("÷", "/")
            .replace("−", "-")
            .replace("π", PI.toString())
            .replace("e", E.toString())
            .replace("√", "sqrt")
        pos = 0

        val result = parseExpression()

        if (pos < expression.length) {
            throw ArithmeticException("Unexpected character: ${expression[pos]}")
        }

        if (result.isInfinite() || result.isNaN()) {
            throw ArithmeticException("Mathematical error")
        }

        return result
    }

    /**
     * Format a result for display.
     * Shows integer results without decimal, otherwise shows up to 10 decimal places.
     */
    fun formatResult(value: Double): String {
        if (value.isNaN()) return "Error"
        if (value.isInfinite()) return if (value > 0) "∞" else "-∞"

        return if (value == value.toLong().toDouble()) {
            value.toLong().toString()
        } else {
            // Format to remove trailing zeros
            val formatted = "%.10f".format(value)
            formatted.trimEnd('0').trimEnd('.')
        }
    }

    // ─── Expression Parsing (Recursive Descent) ───

    // expression = term (('+' | '-') term)*
    private fun parseExpression(): Double {
        var result = parseTerm()
        while (pos < expression.length) {
            skipWhitespace()
            if (pos >= expression.length) break
            when (expression[pos]) {
                '+' -> { pos++; result += parseTerm() }
                '-' -> { pos++; result -= parseTerm() }
                else -> break
            }
        }
        return result
    }

    // term = power (('*' | '/' | '%') power)*
    private fun parseTerm(): Double {
        var result = parsePower()
        while (pos < expression.length) {
            skipWhitespace()
            if (pos >= expression.length) break
            when (expression[pos]) {
                '*' -> { pos++; result *= parsePower() }
                '/' -> {
                    pos++
                    val divisor = parsePower()
                    if (divisor == 0.0) throw ArithmeticException("Division by zero")
                    result /= divisor
                }
                '%' -> {
                    pos++
                    val divisor = parsePower()
                    if (divisor == 0.0) throw ArithmeticException("Division by zero")
                    result %= divisor
                }
                else -> break
            }
        }
        return result
    }

    // power = unary ('^' unary)*
    private fun parsePower(): Double {
        var result = parseUnary()
        skipWhitespace()
        while (pos < expression.length && expression[pos] == '^') {
            pos++
            val exponent = parseUnary()
            result = result.pow(exponent)
            skipWhitespace()
        }
        return result
    }

    // unary = '-'? factorial
    private fun parseUnary(): Double {
        skipWhitespace()
        if (pos < expression.length && expression[pos] == '-') {
            pos++
            return -parseUnary()
        }
        if (pos < expression.length && expression[pos] == '+') {
            pos++
            return parseUnary()
        }
        return parseFactorial()
    }

    // factorial = postfix '!'?
    private fun parseFactorial(): Double {
        val result = parsePostfix()
        skipWhitespace()
        if (pos < expression.length && expression[pos] == '!') {
            pos++
            return factorial(result)
        }
        return result
    }

    // postfix = primary ('(' implicit_mul? ')'? )*  -- handles implicit multiplication like 2π
    private fun parsePostfix(): Double {
        return parsePrimary()
    }

    // primary = number | function '(' expression ')' | '(' expression ')' | constant
    private fun parsePrimary(): Double {
        skipWhitespace()

        if (pos >= expression.length) {
            throw ArithmeticException("Unexpected end of expression")
        }

        // Parenthesized expression
        if (expression[pos] == '(') {
            pos++
            val result = parseExpression()
            skipWhitespace()
            if (pos < expression.length && expression[pos] == ')') {
                pos++
            }
            return result
        }

        // Named functions and constants
        val savedPos = pos
        val name = parseName()
        if (name.isNotEmpty()) {
            skipWhitespace()
            if (pos < expression.length && expression[pos] == '(') {
                // Function call
                pos++
                val arg = parseExpression()
                skipWhitespace()
                if (pos < expression.length && expression[pos] == ')') {
                    pos++
                }
                return when (name) {
                    "sin" -> sin(arg)
                    "cos" -> cos(arg)
                    "tan" -> tan(arg)
                    "log" -> {
                        if (arg <= 0) throw ArithmeticException("log of non-positive number")
                        log10(arg)
                    }
                    "ln" -> {
                        if (arg <= 0) throw ArithmeticException("ln of non-positive number")
                        ln(arg)
                    }
                    "sqrt" -> {
                        if (arg < 0) throw ArithmeticException("Square root of negative number")
                        sqrt(arg)
                    }
                    else -> throw ArithmeticException("Unknown function: $name")
                }
            } else {
                // Constant
                return when (name) {
                    "pi" -> PI
                    "e" -> E
                    "sqrt" -> {
                        // sqrt without parens - treat as error
                        throw ArithmeticException("sqrt requires parentheses")
                    }
                    else -> {
                        // Not a known name, reset and try as number
                        pos = savedPos
                        return parseNumber()
                    }
                }
            }
        }

        // Number
        return parseNumber()
    }

    private fun parseName(): String {
        val start = pos
        while (pos < expression.length && expression[pos].isLetter()) {
            pos++
        }
        return expression.substring(start, pos)
    }

    private fun parseNumber(): Double {
        skipWhitespace()
        val start = pos

        if (pos >= expression.length) {
            throw ArithmeticException("Expected number")
        }

        while (pos < expression.length && (expression[pos].isDigit() || expression[pos] == '.')) {
            pos++
        }

        // Handle scientific notation
        if (pos < expression.length && (expression[pos] == 'e' || expression[pos] == 'E')) {
            if (pos + 1 < expression.length && (expression[pos + 1].isDigit() || expression[pos + 1] == '+' || expression[pos + 1] == '-')) {
                pos++ // skip 'e'
                if (expression[pos] == '+' || expression[pos] == '-') pos++
                while (pos < expression.length && expression[pos].isDigit()) {
                    pos++
                }
            }
        }

        if (start == pos) {
            throw ArithmeticException("Expected number at position $pos")
        }

        return expression.substring(start, pos).toDouble()
    }

    private fun factorial(n: Double): Double {
        if (n < 0) throw ArithmeticException("Factorial of negative number")
        if (n != floor(n)) throw ArithmeticException("Factorial of non-integer")
        if (n > 170) throw ArithmeticException("Factorial overflow")

        var result = 1.0
        for (i in 2..n.toInt()) {
            result *= i
        }
        return result
    }

    private fun skipWhitespace() {
        while (pos < expression.length && expression[pos] == ' ') {
            pos++
        }
    }
}
