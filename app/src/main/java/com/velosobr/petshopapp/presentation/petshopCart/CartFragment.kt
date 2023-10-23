package com.velosobr.petshopapp.presentation.petshopCart

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.velosobr.petshopapp.databinding.FragmentCartBinding
import com.velosobr.petshopapp.domain.model.ProductItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {

    private lateinit var _binding: FragmentCartBinding
    private val binding: FragmentCartBinding get() = _binding
    private val viewModel: CartViewModel by viewModels()

    private lateinit var cartAdapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentCartBinding.inflate(
        inflater,
        container,
        false
    ).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCart()
        binding.button.setOnClickListener {
            viewModel.getCartQuantityItems()
        }
    }

    private fun setupCart() {
        viewModel.fetchCartProductItems()

        viewModel.state.observe(viewLifecycleOwner) { cartState ->
            when (cartState) {
                is CartState.Error -> handleLoadingStateError()
                is CartState.Loading -> handleLoadingStateLoading()
                is CartState.Success -> handleLoadingStateSuccess(cartState)
                is CartState.Share -> handleShareCart(cartState)
            }
        }
    }

    private fun handleLoadingStateError() {
        with(binding) {
            includeViewCartErrorState.root.visibility = View.VISIBLE
            includeViewCartLoadingState.shimmerCartItems.visibility = View.GONE
            recyclerCartItems.visibility = View.GONE
        }
    }

    private fun handleLoadingStateLoading() {
        with(binding) {
            includeViewCartLoadingState.shimmerCartItems.visibility = View.VISIBLE
            includeViewCartErrorState.root.visibility = View.GONE
            recyclerCartItems.visibility = View.GONE
        }
    }

    private fun handleLoadingStateSuccess(cartState: CartState.Success) {
        with(binding) {
            includeViewCartLoadingState.shimmerCartItems.visibility = View.GONE
            includeViewCartErrorState.root.visibility = View.GONE
            recyclerCartItems.visibility = View.VISIBLE
            cartAdapter = CartAdapter(cartState.items)
            with(recyclerCartItems) {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context)
                adapter = cartAdapter
            }
        }
    }

    private fun handleShareCart(cartState: CartState.Share) {
        val shareText = buildShareText(cartState.items)

        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT, shareText)
        sendIntent.type = "text/plain"

        try {
            startActivity(Intent.createChooser(sendIntent, "Compartilhar Carrinho"))
        } catch (e: Exception) {
            Toast.makeText(requireContext(), e.message, Toast.LENGTH_LONG).show()

        }
    }

    private fun buildShareText(items: List<ProductItem>): String {
        val builder = StringBuilder()
        builder.append("Meu Carrinho de Compras:\n")

        for (item in items) {
            builder.append("${item.description} - ${item.amount}\n")
        }

        return builder.toString()
    }
}