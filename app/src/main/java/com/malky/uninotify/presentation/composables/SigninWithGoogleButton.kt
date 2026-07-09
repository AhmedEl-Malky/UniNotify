package com.malky.uninotify.presentation.composables

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularWavyProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.malky.uninotify.R
import com.malky.uninotify.presentation.theme.UniNotifyTheme

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun SignInWithGoogleButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    isLoading: Boolean
) {
    val infiniteTransition = rememberInfiniteTransition()
    val loadingIndicatorColor by infiniteTransition.animateColor(
        initialValue = Color(0xFF4285F4),
        targetValue = Color(0xFF4285F4),
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 2250
                Color(0xFF4285F4) at 0
                Color(0xFFDB4437) at 750
                Color(0xFFF4B400) at 1500
                Color(0xFF0F9D58) at 2250
            },
            repeatMode = RepeatMode.Restart
        )
    )
    OutlinedButton(
        modifier = modifier
            .heightIn(min = 48.dp)
            .animateContentSize(),
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.DarkGray
        ),
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.outline),
    ) {
        AnimatedContent(isLoading) { isLoading ->
            when (isLoading) {
                true -> {
                    CircularWavyProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = loadingIndicatorColor,
                        wavelength = 8.dp,
                        trackColor = MaterialTheme.colorScheme.outline,
                        stroke = Stroke(width = 9f),
                        trackStroke = Stroke(width = 7f)

                    )
                }

                else -> {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(R.drawable.google_logo),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }
            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Continue with Google",
            style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.outlineVariant)
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewSignInWithGoogleButton() {
    UniNotifyTheme {
        SignInWithGoogleButton(
            onClick = {},
            isLoading = false
        )
    }
}