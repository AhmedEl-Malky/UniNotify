package com.malky.uninotify.presentation.composables

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.malky.uninotify.presentation.theme.UniNotifyTheme

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier.heightIn(min = 48.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        content()
    }
}

@Preview
@Composable
private fun PreviewPrimaryButton() {
    UniNotifyTheme {
        PrimaryButton(
            onClick = {},
            content = {}
        )
    }
}