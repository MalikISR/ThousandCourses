package com.example.feature_login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.regex.Pattern

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val errorMessage: String? = null,
    val canLogin: Boolean = false
)

class LoginViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {

    private val emailPattern = Pattern.compile(
        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"
    )

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    fun onEmailChanged(email: String) {
        _uiState.update {
            it.copy(
                email = email,
                errorMessage = null,
                canLogin = validate(email, it.password)
            )
        }
    }

    fun onPasswordChanged(password: String) {
        _uiState.update {
            it.copy(
                password = password,
                errorMessage = null,
                canLogin = validate(it.email, password)
            )
        }
    }

    private fun validate(email: String, password: String) =
        emailPattern.matcher(email).matches() && password.length >= 8

    fun login() {
        val state = _uiState.value
        if (!validate(state.email, state.password)) {
            val errorMsg = when {
                !emailPattern.matcher(state.email).matches() -> "Неправильный формат почты"
                state.password.length < 8 -> "Пароль должен быть минимум 8 символов"
                else -> null
            }
            _uiState.update { it.copy(errorMessage = errorMsg) }
            return
        }

        viewModelScope.launch {
            loginUseCase.login(state.email)
            _uiState.update { it.copy(errorMessage = null) }
        }
    }

    fun isLoggedIn(): Boolean = loginUseCase.isLoggedIn()
}
