package com.malky.uninotify.presentation.authentication.login


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.malky.uninotify.domain.authentication.AuthenticationService
import com.malky.uninotify.domain.authentication.ThirdPartyAuthentication
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
class LoginViewModel @Inject constructor(
    private val authService: AuthenticationService,
    private val thirdPartyAuth: ThirdPartyAuthentication
) : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun onEmailChange(value: String) {
        _state.update {
            it.copy(
                email = value,
                emailValidation = null
            )
        }
    }

    fun onEmailValidate() {
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


    fun onPasswordChange(value: String) {
        _state.update {
            it.copy(
                password = value,
            )
        }
    }

    fun onSignIn(onSuccess: () -> Unit) {
        onEmailValidate()
        if (_state.value.emailValidation == null) {
            _state.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }
            viewModelScope.launch(Dispatchers.IO) {
                authService.signIn(
                    email = _state.value.email,
                    password = _state.value.password
                ).onSuccess {
                    _state.update {
                        it.copy(
                            isGoogleSignInLoading = false,
                            error = null
                        )
                    }
                    withContext(Dispatchers.Main) { onSuccess() }
                }.onError { error ->
                    _state.update {
                        it.copy(
                            isGoogleSignInLoading = false,
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

    fun onSignInWithGoogle(onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update {
                it.copy(
                    isGoogleSignInLoading = true,
                    error = null
                )
            }
            thirdPartyAuth.signInWithGoogle()
                .onSuccess {
                    withContext(Dispatchers.Main) { onSuccess() }
                }.onError { error ->
                    _state.update {
                        it.copy(
                            isGoogleSignInLoading = false,
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