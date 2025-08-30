package com.malky.collegealert.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.composables.icons.lucide.Eye
import com.composables.icons.lucide.EyeOff
import com.composables.icons.lucide.Lucide
import com.malky.collegealert.app.navigation.Destination
import com.malky.collegealert.app.theme.CollegeAlertTheme

@Composable
fun LoginFormSection(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val focusRequester = LocalFocusManager.current
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = "Email",
                style = MaterialTheme.typography.bodyLarge
            )
            PrimaryTextField(
                modifier = Modifier.fillMaxWidth(),
                value = "",
                onValueChange = {},
                placeholder = {
                    Text(
                        text = "Email",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = Color.Gray.copy(
                                alpha = 0.5f
                            )
                        )
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusRequester.moveFocus(FocusDirection.Down)
                    }
                )
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = "Password",
                style = MaterialTheme.typography.bodyLarge
            )
            PrimaryTextField(
                modifier = Modifier.fillMaxWidth(),
                value = "",
                onValueChange = {},
                placeholder = {
                    Text(
                        text = "Password",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = Color.Gray.copy(
                                alpha = 0.5f
                            )
                        )
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { }
                ),
                visualTransformation = if (!isPasswordVisible) PasswordVisualTransformation(mask = '•') else VisualTransformation.None,
                trailingIcon = {
                    IconButton(
                        onClick = {
                            isPasswordVisible = !isPasswordVisible
                        }
                    ) {
                        Icon(
                            imageVector = if (isPasswordVisible) Lucide.EyeOff else Lucide.Eye,
                            contentDescription = "Show/Hide Password",
                            tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                        )
                    }
                }
            )
        }
        PrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            onClick = {
                navController.navigate(Destination.MainGraph)
            },
        ) {
            Text(
                text = "Login",
                style = MaterialTheme.typography.titleLarge
            )
        }
        Text(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .clickable {
                    navController.navigate(Destination.Signup)
                }
                .padding(4.dp),
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                ) {
                    append("Don't have account? ")
                }
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Medium,
                        textDecoration = TextDecoration.Underline
                    )
                ) {
                    append("Signup")
                }
            })
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewLoginFormSection() {
    CollegeAlertTheme {
//        LoginFormSection()
    }
}