package com.example.feature_account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.feature_account.databinding.FragmentAccountBinding
import com.example.feature_login.navigation.LoginNavigator
import kotlinx.coroutines.flow.collectLatest
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AccountViewModel by viewModel()
    private val loginNavigator: LoginNavigator by inject()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collectLatest { state ->
                binding.emailText.text = state.email
            }
        }

        binding.logoutBtn.setOnClickListener {
            viewModel.logout()
            loginNavigator.goToLogin(requireContext())
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
