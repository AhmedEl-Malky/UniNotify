package com.malky.collegealert.presentation.composables

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.malky.collegealert.app.theme.CollegeAlertTheme

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Button(
        onClick = {},
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
    ) {
        content()
    }
}

@Preview
@Composable
private fun PreviewPrimaryButton() {
    CollegeAlertTheme {
        PrimaryButton(
            onClick = {},
            content = {}
        )
    }
}