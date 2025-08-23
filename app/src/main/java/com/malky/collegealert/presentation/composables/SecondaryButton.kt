package com.malky.collegealert.presentation.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.malky.collegealert.app.theme.CollegeAlertTheme

@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    onClick:() -> Unit,
    content:@Composable () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .dropShadow(
                shape = RoundedCornerShape(16.dp),
                shadow = Shadow(
                    radius = 1.5.dp,
                    color = Color.Black.copy(alpha = 0.3f),
                    offset = DpOffset(x = 0.dp, y = 1.1.dp)
                )
            ),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.outline)
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