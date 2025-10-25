package com.example.feature_account

import androidx.lifecycle.ViewModel
import com.example.domain.usecase.GetUserEmailUseCase
import com.example.domain.usecase.IsUserLoggedInUseCase
import com.example.domain.usecase.LogoutUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class AccountUiState(
    val email: String = "",
    val isLoggedIn: Boolean = false
)

class AccountViewModel(
    private val getUserEmailUseCase: GetUserEmailUseCase,
    private val isUserLoggedInUseCase: IsUserLoggedInUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        AccountUiState(
            email = getUserEmailUseCase() ?: "",
            isLoggedIn = isUserLoggedInUseCase()
        )
    )
    val uiState: StateFlow<AccountUiState> = _uiState

    fun logout() {
        logoutUseCase()
        _uiState.value = AccountUiState(email = "", isLoggedIn = false)
    }
}
