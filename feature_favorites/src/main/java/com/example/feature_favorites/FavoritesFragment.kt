package com.example.feature_favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.feature_favorites.databinding.FragmentFavoritesBinding
import com.example.feature_main.adapter.CoursesAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    private val vm: FavoritesViewModel by viewModel()
    private lateinit var adapter: CoursesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = CoursesAdapter { course ->
            vm.removeFavorite(course)
        }

        binding.favoritesRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.favoritesRecycler.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            vm.favorites.collectLatest { list ->
                adapter.submitList(list)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewLifecycleOwner.lifecycleScope.launch {
            vm.refreshFavorites()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
