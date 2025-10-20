package com.malky.uninotify.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialShapes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.toShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.google.firebase.auth.FirebaseUser
import com.malky.uninotify.R
import com.malky.uninotify.app.theme.UniNotifyTheme

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun PersonalInformationCard(
    modifier: Modifier = Modifier,
    user: FirebaseUser?
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .dropShadow(
                shape = RoundedCornerShape(16.dp),
                shadow = Shadow(
                    radius = 2.dp,
                    color = Color.Black.copy(alpha = 0.3f),
                    offset = DpOffset(x = 0.dp, y = 0.5.dp)
                )
            ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 48.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            var isImageFailed by remember { mutableStateOf(false) }
            AsyncImage(
                modifier = Modifier
                    .size(72.dp)
                    .clip(MaterialShapes.Cookie12Sided.toShape())
                    .background(MaterialTheme.colorScheme.background)
                    .border(
                        if (isImageFailed) 1.8.dp else 0.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = MaterialShapes.Cookie12Sided.toShape()
                    ),
                model = user?.photoUrl,
                error = painterResource(R.drawable.avatar),
                onError = {
                    isImageFailed = true
                },
                contentDescription = "Logo",
            )
//                Box(
//                    modifier = Modifier
//                        .size(72.dp)
//                        .background(
//                            color = MaterialTheme.colorScheme.primary,
//                            shape = MaterialShapes.Cookie12Sided.toShape()
//                        ),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Text(
//                        text = LocalUser.current!!.displayName!!.first().toString(),
//                        style = MaterialTheme.typography.displaySmall.copy(
//                            fontWeight = FontWeight.Medium,
//                            color = MaterialTheme.colorScheme.onPrimary
//                        ),
//                        textAlign = TextAlign.Center
//                    )
            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                user?.displayName?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.W700)
                    )
                }
                Text(
                    text = "Computer Science",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onSurface.copy(
                            alpha = 0.75f
                        )
                    )
                )
                Text(
                    text = "Student ID: 2131007",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onSurface.copy(
                            alpha = 0.75f
                        )
                    )
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewPersonalInformationCard() {
    UniNotifyTheme {
//        PersonalInformationCard()
    }
}