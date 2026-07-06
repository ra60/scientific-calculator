package com.calculator.scientific.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class ButtonType {
    NUMBER, OPERATOR, FUNCTION, EQUALS, CLEAR, BACKSPACE
}

@Composable
fun CalculatorButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonType: ButtonType = ButtonType.NUMBER,
    isCompact: Boolean = false,
    span: Int = 1
) {
    val colors = MaterialTheme.colorScheme

    val (backgroundColor, contentColor, fontWeight) = when (buttonType) {
        ButtonType.NUMBER -> Triple(
            colors.surfaceVariant,
            colors.onSurfaceVariant,
            FontWeight.Medium
        )
        ButtonType.OPERATOR -> Triple(
            colors.primaryContainer,
            colors.onPrimaryContainer,
            FontWeight.Bold
        )
        ButtonType.FUNCTION -> Triple(
            colors.tertiaryContainer,
            colors.onTertiaryContainer,
            FontWeight.Medium
        )
        ButtonType.EQUALS -> Triple(
            colors.primary,
            colors.onPrimary,
            FontWeight.Bold
        )
        ButtonType.CLEAR -> Triple(
            colors.errorContainer,
            colors.onErrorContainer,
            FontWeight.Bold
        )
        ButtonType.BACKSPACE -> Triple(
            colors.errorContainer.copy(alpha = 0.6f),
            colors.onErrorContainer,
            FontWeight.Medium
        )
    }

    val height = if (isCompact) 48.dp else 64.dp
    val fontSize = if (isCompact) 16.sp else 22.sp

    Box(
        modifier = modifier
            .height(height)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .clickable(onClick = onClick)
            .padding(horizontal = 4.dp, vertical = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = contentColor,
            fontSize = fontSize,
            fontWeight = fontWeight,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}
