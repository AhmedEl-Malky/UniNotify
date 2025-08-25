package com.malky.collegealert.presentation.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.malky.collegealert.app.theme.CollegeAlertTheme

@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    onClick:() -> Unit,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onSurface
    ),
    border: BorderStroke = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.outline),
    content:@Composable () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    Button(
        onClick = onClick,
        interactionSource = interactionSource,
        modifier = modifier
            .shadow(
                elevation = if (isPressed) 0.dp else 0.75.dp,
                shape = RoundedCornerShape(16.dp)
            ),
        shape = RoundedCornerShape(16.dp),
        colors = colors,
        border = border
    ) {
        content()
    }
}

@Preview
@Composable
private fun PreviewSecondaryButton() {
    CollegeAlertTheme {
        SecondaryButton(
            onClick = {},
            content = {}
        )
    }
}