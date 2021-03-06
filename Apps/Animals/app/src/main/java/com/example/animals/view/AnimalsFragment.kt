package com.example.animals.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.animals.databinding.FragmentAnimalsBinding
import com.example.animals.util.gone
import com.example.animals.util.visible
import com.example.animals.viewmodel.AnimalsViewModel

class AnimalsFragment : Fragment() {

    private val viewModel: AnimalsViewModel by viewModels()
    private val animalsAdapter = AnimalsAdapter()
    private lateinit var binding: FragmentAnimalsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnimalsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        setRefreshLayout()
        observeViewModel()
    }

    override fun onStart() {
        super.onStart()
        viewModel.refresh()
    }

    private fun setRecyclerView() {
        binding.rvAnimals.adapter = animalsAdapter
    }

    private fun setRefreshLayout() {
        binding.refreshLayout.setOnRefreshListener { viewModel.hardRefresh() }
    }

    private fun observeViewModel() {
        viewModel.animals.observe(viewLifecycleOwner) { list ->
            if (list != null) {
                binding.rvAnimals.visible()
                animalsAdapter.updateAnimalList(list)
            }
        }
        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            with(binding) {
                if (isLoading) {
                    refreshLayout.isRefreshing = true
                    rvAnimals.gone()
                } else {
                    refreshLayout.isRefreshing = false
                }
            }
        }
        viewModel.loadError.observe(viewLifecycleOwner) { hasError ->
            if (hasError) binding.tvAnimalsError.visible() else binding.tvAnimalsError.gone()
        }
    }
}