package com.velosobr.petshopapp.presentation.petshopHomeItems

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.velosobr.petshopapp.databinding.FragmentHomeItemsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeItemsFragment : Fragment() {

    private var _binding: FragmentHomeItemsBinding? = null
    private val binding: FragmentHomeItemsBinding get() = _binding!!

    private val viewModel: HomeItemsViewModel by viewModels()

    private lateinit var homeItemsAdapter: HomeItemsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentHomeItemsBinding.inflate(
        inflater,
        container,
        false
    ).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupHomeItems()

    }

    private fun setupHomeItems() {
        homeItemsAdapter = HomeItemsAdapter()
        with(binding.recyclerPetshopItems) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = homeItemsAdapter
        }

        viewModel.fetchPetshopProductItems()

        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is HomeItensState.Error ->{
                    binding.includeViewPetshopItemsErrorState.root.visibility = View.VISIBLE
                    binding.includeViewHomeItemsLoadingState.shimmerPetshopItems.visibility =
                        View.GONE
                    binding.recyclerPetshopItems.visibility = View.GONE

                }

                is HomeItensState.Loading -> {
                    binding.includeViewHomeItemsLoadingState.shimmerPetshopItems.visibility =
                        View.VISIBLE
                    binding.includeViewPetshopItemsErrorState.root.visibility = View.GONE

                    binding.recyclerPetshopItems.visibility = View.GONE
                }

                is HomeItensState.Success -> {
                    binding.includeViewHomeItemsLoadingState.shimmerPetshopItems.visibility =
                        View.GONE
                    binding.includeViewPetshopItemsErrorState.root.visibility = View.GONE

                    binding.recyclerPetshopItems.visibility = View.VISIBLE

                    homeItemsAdapter.productItemList = state.items
                }
            }

        }
    }

}