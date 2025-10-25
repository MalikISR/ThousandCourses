package com.example.feature_main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.feature_main.databinding.FragmentHomeBinding
import com.example.feature_main.adapter.CoursesAdapter
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val vm: HomeViewModel by viewModel()
    private lateinit var adapter: CoursesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = CoursesAdapter { course ->
            vm.toggleLike(course)
        }
        binding.coursesRecycler.adapter = adapter
        binding.coursesRecycler.layoutManager = LinearLayoutManager(requireContext())

        lifecycleScope.launchWhenStarted {
            vm.courses.collectLatest { list ->
                adapter.submitList(list)
            }
        }

        binding.sortText.setOnClickListener {
            vm.filterByDate()
        }
    }

    override fun onResume() {
        super.onResume()
        vm.refresh()
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
