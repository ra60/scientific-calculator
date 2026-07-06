package com.calculator.scientific.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ModeToggle(
    isScientificMode: Boolean,
    onToggle: () -> Unit,
    basicLabel: String,
    scientificLabel: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        val basicBg by animateColorAsState(
            targetValue = if (!isScientificMode) MaterialTheme.colorScheme.primary
                          else MaterialTheme.colorScheme.surfaceVariant,
            label = "basicBg"
        )
        val basicText by animateColorAsState(
            targetValue = if (!isScientificMode) MaterialTheme.colorScheme.onPrimary
                          else MaterialTheme.colorScheme.onSurfaceVariant,
            label = "basicText"
        )

        val scientificBg by animateColorAsState(
            targetValue = if (isScientificMode) MaterialTheme.colorScheme.primary
                          else MaterialTheme.colorScheme.surfaceVariant,
            label = "scientificBg"
        )
        val scientificText by animateColorAsState(
            targetValue = if (isScientificMode) MaterialTheme.colorScheme.onPrimary
                          else MaterialTheme.colorScheme.onSurfaceVariant,
            label = "scientificText"
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(20.dp))
                .background(basicBg)
                .clickable(enabled = isScientificMode) { onToggle() }
                .padding(vertical = 10.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = basicLabel,
                color = basicText,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.width(4.dp))

        Box(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(20.dp))
                .background(scientificBg)
                .clickable(enabled = !isScientificMode) { onToggle() }
                .padding(vertical = 10.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = scientificLabel,
                color = scientificText,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
