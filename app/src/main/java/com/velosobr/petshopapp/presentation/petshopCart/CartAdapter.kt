package com.velosobr.petshopapp.presentation.petshopCart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.velosobr.petshopapp.R
import com.velosobr.petshopapp.databinding.CartProductItemBinding
import com.velosobr.petshopapp.databinding.PetshopProductItemBinding
import com.velosobr.petshopapp.domain.model.ProductItem

class CartAdapter(
    products: List<ProductItem>,
) : RecyclerView.Adapter<CartAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: CartProductItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var productItemList: MutableList<ProductItem> = products.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            CartProductItemBinding.inflate(
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

            imageCartItem.load(currentProductItem.imageUrl) {
                crossfade(true)
                crossfade(1000)
            }

            productItemList
        }
    }
}