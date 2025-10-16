package com.malky.uninotify.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.CircularWavyProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
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
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.composables.icons.lucide.Eye
import com.composables.icons.lucide.EyeOff
import com.composables.icons.lucide.Lucide
import com.malky.uninotify.app.navigation.Destination
import com.malky.uninotify.presentation.authentication.signup.SignupAction
import com.malky.uninotify.presentation.authentication.signup.SignupState

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun SignupForm(
    modifier: Modifier = Modifier,
    state: SignupState,
    onAction: (SignupAction) -> Unit,
    updateUser:() -> Unit,
    navController: NavHostController
) {
    val focusRequester = LocalFocusManager.current
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }
    var isConfirmPasswordVisible by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "First Name",
                    style = MaterialTheme.typography.bodyLarge
                )
                PrimaryTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.firstName,
                    onValueChange = { value ->
                        onAction(SignupAction.OnFirstNameChange(value))
                    },
                    placeholder = {
                        Text(
                            text = "First Name",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = Color.Gray.copy(
                                    alpha = 0.5f
                                )
                            )
                        )
                    },
                    isError = state.firstNameValidation != null,
                    supportingText = {
                        state.firstNameValidation?.let {
                            Text(
                                text = it.asString(),
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusRequester.moveFocus(FocusDirection.Next)
                            onAction(SignupAction.OnFirstNameValidate)
                        }
                    )
                )
            }
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Last Name",
                    style = MaterialTheme.typography.bodyLarge
                )
                PrimaryTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.lastName,
                    onValueChange = { value ->
                        onAction(SignupAction.OnLastNameChange(value))
                    },
                    placeholder = {
                        Text(
                            text = "Last Name",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = Color.Gray.copy(
                                    alpha = 0.5f
                                )
                            )
                        )
                    },
                    isError = state.lastNameValidation != null,
                    supportingText = {
                        state.lastNameValidation?.let {
                            Text(
                                text = it.asString(),
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusRequester.moveFocus(FocusDirection.Next)
                            onAction(SignupAction.OnLastNameValidate)
                        }
                    ),
                )
            }
        }
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
                onValueChange = { value ->
                    onAction(SignupAction.OnEmailChange(value))
                },
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
                        focusRequester.moveFocus(FocusDirection.Down)
                        onAction(SignupAction.OnEmailValidate)
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
                onValueChange = { value ->
                    onAction(SignupAction.OnPasswordChange(value))
                },
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
                isError = state.passwordValidation != null,
                supportingText = {
                    state.passwordValidation?.let {
                        Text(
                            text = it.asString(),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusRequester.moveFocus(FocusDirection.Next)
                        onAction(SignupAction.OnPasswordValidate)
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
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = "Confirm Password",
                style = MaterialTheme.typography.bodyLarge
            )
            PrimaryTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.confirmPassword,
                onValueChange = { value ->
                    onAction(SignupAction.OnConfirmPasswordChange(value))
                },
                placeholder = {
                    Text(
                        text = "Confirm Password",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = Color.Gray.copy(
                                alpha = 0.5f
                            )
                        )
                    )
                },
                isError = state.confirmPasswordValidation != null,
                supportingText = {
                    state.confirmPasswordValidation?.let {
                        Text(
                            text = it.asString(),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusRequester.clearFocus()
                        onAction(SignupAction.OnConfirmPasswordValidate)
                    }
                ),
                visualTransformation = if (!isConfirmPasswordVisible) PasswordVisualTransformation(
                    mask = '•'
                ) else VisualTransformation.None,
                trailingIcon = {
                    IconButton(
                        onClick = {
                            isConfirmPasswordVisible = !isConfirmPasswordVisible
                        }
                    ) {
                        Icon(
                            imageVector = if (isConfirmPasswordVisible) Lucide.EyeOff else Lucide.Eye,
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
                onAction(
                    SignupAction.OnSignup(
                        onSuccess = {
                            updateUser()
                            navController.navigate(Destination.MainGraph){
                                popUpTo(Destination.AuthenticationGraph){
                                    inclusive = true
                                }
                            }
                        }
                    )
                )
            }
        ) {
            if (state.isLoading)
                CircularWavyProgressIndicator(
                    modifier = Modifier.size(32.dp),
                    color = MaterialTheme.colorScheme.onPrimary,
                    wavelength = 8.dp,
                )
            else
                Text(
                    text = "Signup",
                    style = MaterialTheme.typography.titleLarge
                )
        }
    }
}