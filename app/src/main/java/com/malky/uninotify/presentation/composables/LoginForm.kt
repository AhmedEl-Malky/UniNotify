package com.malky.uninotify.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularWavyProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.HorizontalDivider
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
import com.malky.uninotify.app.navigation.Destination
import com.malky.uninotify.app.theme.UniNotifyTheme
import com.malky.uninotify.presentation.authentication.login.LoginState

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun LoginForm(
    modifier: Modifier = Modifier,
    state: LoginState,
    onEmailChange: (String) -> Unit,
    onEmailValidate: () -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignIn: (() -> Unit) -> Unit,
    onSignInWithGoogle: (() -> Unit) -> Unit,
    updateUser:() -> Unit,
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
                value = state.email,
                onValueChange = { onEmailChange(it) },
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
                isError = state.emailValidation != null,
                supportingText = {
                    state.emailValidation?.let {
                        Text(
                            text = it.asString(),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        onEmailValidate()
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
                value = state.password,
                onValueChange = { onPasswordChange(it) },
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
                    onDone = {
                        focusRequester.clearFocus()
                    }
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
                focusRequester.clearFocus()
                onSignIn {
                    updateUser()
                    navController.navigate(Destination.MainGraph) {
                        popUpTo(Destination.AuthenticationGraph) {
                            inclusive = true
                        }
                    }
                }
            },
        ) {
            if (state.isLoading)
                CircularWavyProgressIndicator(
                    modifier = Modifier.size(32.dp),
                    color = MaterialTheme.colorScheme.onPrimary,
                    wavelength = 8.dp,
                )
            else
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(
                modifier = Modifier.weight(1f),
                thickness = 0.8.dp,
                color = MaterialTheme.colorScheme.outline
            )
            Text(
                modifier = Modifier.padding(horizontal = 12.dp),
                text = "or",
                style = MaterialTheme.typography.bodyLarge
            )
            HorizontalDivider(
                modifier = Modifier.weight(1f),
                thickness = 0.8.dp,
                color = MaterialTheme.colorScheme.outline
            )
        }
        SignInWithGoogleButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            isLoading = state.isGoogleSignInLoading,
            onClick = {
                onSignInWithGoogle {
                    updateUser()
                    navController.navigate(Destination.MainGraph) {
                        popUpTo(Destination.AuthenticationGraph) {
                            inclusive = true
                        }
                    }
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewLoginFormSection() {
    UniNotifyTheme {
//        LoginFormSection()
    }
}