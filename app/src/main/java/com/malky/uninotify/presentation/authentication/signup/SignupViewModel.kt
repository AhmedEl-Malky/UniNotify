package com.malky.uninotify.presentation.authentication.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.malky.uninotify.data.remote.AuthenticationService
import com.malky.uninotify.utils.UserDataValidator
import com.malky.uninotify.data.utils.onError
import com.malky.uninotify.data.utils.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val authService: AuthenticationService,
) : ViewModel(), SignupInteractionListener {
    private val _state = MutableStateFlow(SignupState())
    val state = _state.asStateFlow()

    private val eventsChannel = Channel<SignupEvents>()
    val events = eventsChannel.receiveAsFlow()


    override fun onFirstNameChange(value: String) {
        _state.update {
            it.copy(
                firstName = value,
                firstNameValidation = null
            )
        }
    }

    override fun onFirstNameValidate() {
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


    override fun onLastNameChange(value: String) {
        _state.update {
            it.copy(
                lastName = value,
                lastNameValidation = null
            )
        }
    }

    override fun onLastNameValidate() {
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

    override fun onEmailChange(value: String) {
        _state.update {
            it.copy(
                email = value,
                emailValidation = null
            )
        }
    }

    override fun onEmailValidate() {
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

    override fun onPasswordChange(value: String) {
        _state.update {
            it.copy(
                password = value,
                passwordValidation = null
            )
        }
    }

    override fun onPasswordValidate() {
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

    override fun onConfirmPasswordChange(value: String) {
        _state.update {
            it.copy(
                confirmPassword = value,
                confirmPasswordValidation = null
            )
        }
    }

    override fun onConfirmPasswordValidate() {
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

    override fun onSignup(onSuccess: () -> Unit) {
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
                        )
                    }
                    withContext(Dispatchers.Main) { onSuccess() }
                }.onError { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                        )
                    }
                    eventsChannel.send(SignupEvents.OnError(error.toUiText()))
                }
            }
        }
    }
}