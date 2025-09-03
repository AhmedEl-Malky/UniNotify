package com.malky.uninotify.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.Eye
import com.composables.icons.lucide.EyeOff
import com.composables.icons.lucide.Lucide

@Composable
fun SignupFormSection(
    modifier: Modifier = Modifier,
//    navController: NavHostController
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
                    value = "",
                    onValueChange = {},
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
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusRequester.moveFocus(FocusDirection.Next)
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
                    value = "",
                    onValueChange = {},
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
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusRequester.moveFocus(FocusDirection.Next)
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
                value = "fafafa",
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
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusRequester.moveFocus(FocusDirection.Next)
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
                value = "",
                onValueChange = {},
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
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { }
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
//                navController.navigate(Destination.MainGraph)
            },

            ) {
            Text(
                text = "Signup",
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}