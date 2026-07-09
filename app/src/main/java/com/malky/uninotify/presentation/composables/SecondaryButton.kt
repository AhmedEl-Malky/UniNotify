package com.malky.uninotify.presentation.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.malky.uninotify.presentation.theme.UniNotifyTheme

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
    Button(
        onClick = onClick,
        modifier = modifier.heightIn(min = 48.dp),
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
    UniNotifyTheme {
        SecondaryButton(
            onClick = {},
            content = {}
        )
    }
}