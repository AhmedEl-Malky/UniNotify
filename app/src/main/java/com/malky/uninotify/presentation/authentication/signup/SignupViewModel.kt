package com.malky.uninotify.presentation.authentication.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.malky.uninotify.domain.authentication.AuthenticationService
import com.malky.uninotify.utils.UserDataValidator
import com.malky.uninotify.utils.onError
import com.malky.uninotify.utils.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val authService: AuthenticationService,
) : ViewModel() {
    private val _state = MutableStateFlow(SignupState())
    val state = _state.asStateFlow()

    fun onAction(action: SignupAction) {
        when (action) {
            is SignupAction.OnFirstNameChange -> onFirstNameChange(action.value)
            is SignupAction.OnFirstNameValidate -> onFirstNameValidate()
            is SignupAction.OnLastNameChange -> onLastNameChange(action.value)
            is SignupAction.OnLastNameValidate -> onLastNameValidate()
            is SignupAction.OnEmailChange -> onEmailChange(action.value)
            is SignupAction.OnEmailValidate -> onEmailValidate()
            is SignupAction.OnPasswordChange -> onPasswordChange(action.value)
            is SignupAction.OnPasswordValidate -> onPasswordValidate()
            is SignupAction.OnConfirmPasswordChange -> onConfirmPasswordChange(action.value)
            is SignupAction.OnConfirmPasswordValidate -> onConfirmPasswordValidate()
            is SignupAction.OnSignup -> onSignup(action.onSuccess)
        }
    }

    private fun onFirstNameChange(value: String) {
        _state.update {
            it.copy(
                firstName = value,
                firstNameValidation = null
            )
        }
    }

    private fun onFirstNameValidate() {
        UserDataValidator.validateName(_state.value.firstName)
            .onSuccess {
                _state.update {
                    it.copy(
                        firstNameValidation = null
                    )
                }
            }
            .onError { error ->
                _state.update {
                    it.copy(
                        firstNameValidation = error.toUiText()
                    )
                }
            }
    }


    private fun onLastNameChange(value: String) {
        _state.update {
            it.copy(
                lastName = value,
                lastNameValidation = null
            )
        }
    }

    private fun onLastNameValidate() {
        UserDataValidator.validateName(_state.value.lastName)
            .onSuccess {
                _state.update {
                    it.copy(
                        lastNameValidation = null
                    )
                }
            }
            .onError { error ->
                _state.update {
                    it.copy(
                        lastNameValidation = error.toUiText()
                    )
                }
            }
    }

    private fun onEmailChange(value: String) {
        _state.update {
            it.copy(
                email = value,
                emailValidation = null
            )
        }
    }

    private fun onEmailValidate() {
        UserDataValidator.validateEmail(_state.value.email)
            .onSuccess {
                _state.update {
                    it.copy(
                        emailValidation = null
                    )
                }
            }
            .onError { error ->
                _state.update {
                    it.copy(
                        emailValidation = error.toUiText()
                    )
                }
            }
    }

    private fun onPasswordChange(value: String) {
        _state.update {
            it.copy(
                password = value,
                passwordValidation = null
            )
        }
    }

    private fun onPasswordValidate() {
        UserDataValidator.validatePassword(_state.value.password)
            .onSuccess {
                _state.update {
                    it.copy(
                        passwordValidation = null
                    )
                }
            }
            .onError { error ->
                _state.update {
                    it.copy(
                        passwordValidation = error.toUiText()
                    )
                }
            }
    }

    private fun onConfirmPasswordChange(value: String) {
        _state.update {
            it.copy(
                confirmPassword = value,
                confirmPasswordValidation = null
            )
        }
    }

    private fun onConfirmPasswordValidate() {
        UserDataValidator.validateConfirmPassword(
            password = _state.value.password,
            confirmPassword = _state.value.confirmPassword
        )
            .onSuccess {
                _state.update {
                    it.copy(
                        confirmPasswordValidation = null
                    )
                }
            }
            .onError { error ->
                _state.update {
                    it.copy(
                        confirmPasswordValidation = error.toUiText()
                    )
                }
            }
    }

    private fun onValidateSignupData() {
        onFirstNameValidate()
        onLastNameValidate()
        onEmailValidate()
        onPasswordValidate()
        onConfirmPasswordValidate()
    }

    private fun onSignup(onSuccess: () -> Unit) {
        onValidateSignupData()
        if (
            _state.value.firstNameValidation == null &&
            _state.value.lastNameValidation == null &&
            _state.value.emailValidation == null &&
            _state.value.passwordValidation == null &&
            _state.value.confirmPasswordValidation == null
        ) {
            _state.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }
            viewModelScope.launch {
                authService.signUp(
                    firstName = _state.value.firstName,
                    lastName = _state.value.lastName,
                    email = _state.value.email,
                    password = _state.value.password
                ).onSuccess { user ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = null
                        )
                    }
                    withContext(Dispatchers.Main){ onSuccess() }
                }.onError { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = error.toUiText()
                        )
                    }
                }
                delay(500)
                _state.update {
                    it.copy(
                        error = null
                    )
                }
            }
        }
    }
}