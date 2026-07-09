package com.malky.uninotify.presentation.authentication.login


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.malky.uninotify.data.remote.AuthenticationService
import com.malky.uninotify.data.remote.ThirdPartyAuthentication
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
class LoginViewModel @Inject constructor(
    private val authService: AuthenticationService,
    private val thirdPartyAuth: ThirdPartyAuthentication
) : ViewModel(), LoginInteractionListener {
    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    private val eventsChannel = Channel<LoginEvents>()
    val events = eventsChannel.receiveAsFlow()

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
            )
        }
    }

    override fun onSignIn(onSuccess: () -> Unit) {
        onEmailValidate()
        if (_state.value.emailValidation == null) {
            viewModelScope.launch(Dispatchers.IO) {
                _state.update {
                    it.copy(
                        isLoading = true,
                    )
                }
                authService.signIn(
                    email = _state.value.email,
                    password = _state.value.password
                ).onSuccess {
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
                    eventsChannel.send(LoginEvents.OnError(error.toUiText()))
                }
            }
        }
    }

    override fun onSignInWithGoogle(onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update {
                it.copy(
                    isGoogleSignInLoading = true,
                )
            }
            thirdPartyAuth.signInWithGoogle()
                .onSuccess {
                    withContext(Dispatchers.Main) { onSuccess() }
                }.onError { error ->
                    _state.update {
                        it.copy(
                            isGoogleSignInLoading = false,
                        )
                    }
                    eventsChannel.send(LoginEvents.OnError(error.toUiText()))
                }
        }
    }
}