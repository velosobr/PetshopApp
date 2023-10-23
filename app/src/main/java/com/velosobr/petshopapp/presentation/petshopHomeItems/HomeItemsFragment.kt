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
import com.velosobr.petshopapp.presentation.petshopItemDetails.DetailsFragment
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
        viewModel.getCartQuantityItems()
        configureBadgeCount()
    }

    private fun setupHomeItems() {
        viewModel.fetchPetShopProductItems()

        viewModel.state.observe(viewLifecycleOwner) { homeItemsState ->
            when (homeItemsState) {
                is HomeItemsState.Error -> handleLoadingStateError()
                is HomeItemsState.Loading -> handleLoadingStateLoading()
                is HomeItemsState.Success -> handleLoadingStateSuccess(homeItemsState)
                is HomeItemsState.CartItemAdded -> configureBadgeCount(homeItemsState.count)
                is HomeItemsState.CartItemRemoved -> configureBadgeCount(homeItemsState.count)
                is HomeItemsState.CartError -> handleCartError(homeItemsState.message)
                is HomeItemsState.CartItemsQuantity -> configureBadgeCount(homeItemsState.count)
            }
        }
    }

    private fun handleLoadingStateError() {
        with(binding) {
            includeViewPetshopItemsErrorState.root.visibility = View.VISIBLE
            includeViewHomeItemsLoadingState.shimmerPetshopItems.visibility = View.GONE
            recyclerPetshopItems.visibility = View.GONE
        }
    }

    private fun handleLoadingStateLoading() {
        with(binding) {
            includeViewHomeItemsLoadingState.shimmerPetshopItems.visibility = View.VISIBLE
            includeViewPetshopItemsErrorState.root.visibility = View.GONE
            recyclerPetshopItems.visibility = View.GONE
        }
    }

    private fun handleLoadingStateSuccess(homeItemsState: HomeItemsState.Success) {
        with(binding) {
            includeViewHomeItemsLoadingState.shimmerPetshopItems.visibility = View.GONE
            includeViewPetshopItemsErrorState.root.visibility = View.GONE
            recyclerPetshopItems.visibility = View.VISIBLE
            homeItemsAdapter = HomeItemsAdapter(
                homeItemsState.items,
                viewModel::updateCart,
                ::onClickButtonCallDetails
            )

            with(recyclerPetshopItems) {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context)
                adapter = homeItemsAdapter
            }
        }
    }

    private fun handleCartError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    private fun onClickButtonCallDetails() {
        findNavController().navigate(R.id.detailsFragment)
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
}