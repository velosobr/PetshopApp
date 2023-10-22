package com.velosobr.petshopapp.presentation.petshopHomeItems

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import com.google.android.material.badge.ExperimentalBadgeUtils
import com.velosobr.petshopapp.R
import com.velosobr.petshopapp.databinding.FragmentHomeItemsBinding
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalBadgeUtils
@AndroidEntryPoint
class HomeItemsFragment : Fragment() {

    private lateinit var _binding: FragmentHomeItemsBinding
    private val binding: FragmentHomeItemsBinding get() = _binding

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
        configureBadgeCount()
    }

    private fun configureBadgeCount(count: Int = 0) {
        val toolbar: MaterialToolbar = binding.tollbar
        val badgeDrawable = BadgeDrawable.create(requireContext()).apply {
            isVisible = true
            backgroundColor = ContextCompat.getColor(requireContext(), R.color.black)
            number = count
        }

        toolbar.setOnMenuItemClickListener {
            findNavController().navigate(R.id.cartFragment)
            true
        }

        BadgeUtils.attachBadgeDrawable(badgeDrawable, toolbar, R.id.cart)
    }

    private fun setupHomeItems() {
        homeItemsAdapter = HomeItemsAdapter {
            viewModel.updateCart(it)
        }
        with(binding.recyclerPetshopItems) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = homeItemsAdapter
        }

        viewModel.fetchPetShopProductItems()

        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is HomeItemsState.Error -> {
                    binding.includeViewPetshopItemsErrorState.root.visibility = View.VISIBLE
                    binding.includeViewHomeItemsLoadingState.shimmerPetshopItems.visibility =
                        View.GONE
                    binding.recyclerPetshopItems.visibility = View.GONE
                }

                is HomeItemsState.Loading -> {
                    binding.includeViewHomeItemsLoadingState.shimmerPetshopItems.visibility =
                        View.VISIBLE
                    binding.includeViewPetshopItemsErrorState.root.visibility = View.GONE
                    binding.recyclerPetshopItems.visibility = View.GONE
                }

                is HomeItemsState.Success -> {
                    binding.includeViewHomeItemsLoadingState.shimmerPetshopItems.visibility =
                        View.GONE
                    binding.includeViewPetshopItemsErrorState.root.visibility = View.GONE
                    binding.recyclerPetshopItems.visibility = View.VISIBLE
                    homeItemsAdapter.productItemList = state.items
                }

                is HomeItemsState.CartItemAdded -> configureBadgeCount(state.count)
                is HomeItemsState.CartItemRemoved -> configureBadgeCount(state.count)
                is HomeItemsState.CartError -> Toast.makeText(
                    requireContext(),
                    state.message,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}