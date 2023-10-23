package com.velosobr.petshopapp.presentation.petshopHomeItems

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import androidx.navigation.fragment.findNavController

import com.velosobr.petshopapp.R
import com.velosobr.petshopapp.databinding.PetshopProductItemBinding
import com.velosobr.petshopapp.domain.model.ProductItem

class HomeItemsAdapter(
    private val products: List<ProductItem>,
    private val onClickButton: (ProductItem) -> Unit,
    private val onClickButtonCallDetails: () -> Unit
) : RecyclerView.Adapter<HomeItemsAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: PetshopProductItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var productItemList: MutableList<ProductItem> = products.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            PetshopProductItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount() = productItemList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentProductItem = productItemList[position]

        holder.binding.apply {
            descriptionText.text = currentProductItem.description
            amountText.text =
                root.context.getString(
                    R.string.valor_amount,
                    currentProductItem.amount
                )

            imagePetshopItem.load(currentProductItem.imageUrl) {
                crossfade(true)
                crossfade(1000)
            }
            imagePetshopItem.setOnClickListener {
                onClickButtonCallDetails()
            }

            productCartButton.setOnClickListener {
                val productSelected =
                    currentProductItem.copy(isAddedToCart = !currentProductItem.isAddedToCart)

                productItemList = productItemList.map { productItem ->
                    if (productItem.id == currentProductItem.id) {
                        productSelected
                    } else {
                        productItem
                    }
                }.toMutableList()
                productCartButton.text = if (productSelected.isAddedToCart) "- remove" else "+ add"
                notifyDataSetChanged()
                onClickButton(productSelected)

            }

        }
    }
}