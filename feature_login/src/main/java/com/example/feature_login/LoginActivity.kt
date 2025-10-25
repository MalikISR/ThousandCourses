package com.example.feature_login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.example.feature_login.databinding.ActivityLoginBinding
import com.example.feature_login.navigation.LoginNavigator
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.android.ext.android.inject
import androidx.core.net.toUri

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModel()
    private val navigator: LoginNavigator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (viewModel.isLoggedIn()) {
            navigator.onLoginSuccess(this)
            finish()
            return
        }

        setupUi()
    }

    private fun setupUi() {
        binding.emailEdit.addTextChangedListener { viewModel.onEmailChanged(it.toString()) }
        binding.passwordEdit.addTextChangedListener { viewModel.onPasswordChanged(it.toString()) }

        binding.loginBtn.setOnClickListener {
            viewModel.login()
            if (viewModel.isLoggedIn()) {
                navigator.onLoginSuccess(this)
                finish()
            }
        }

        binding.vkBtn.setOnClickListener { openUrl("https://vk.com") }
        binding.okBtn.setOnClickListener { openUrl("https://ok.ru") }

        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { state ->
                binding.loginBtn.isEnabled = state.canLogin
                state.errorMessage?.let {
                    Toast.makeText(this@LoginActivity, it, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, url.toUri())
        startActivity(intent)
    }
}
