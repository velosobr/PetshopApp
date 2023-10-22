package com.velosobr.petshopapp.presentation.petshopHomeItems

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.velosobr.petshopapp.databinding.PetshopProductItemBinding
import com.velosobr.petshopapp.domain.model.ProductItem

class HomeItemsAdapter : RecyclerView.Adapter<HomeItemsAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: PetshopProductItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallBack = object : DiffUtil.ItemCallback<ProductItem>() {
        override fun areItemsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
            return newItem == oldItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallBack)

    var productItemList: List<ProductItem>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

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

            imagePetshopItem.load(currentProductItem.imageUrl) {
                crossfade(true)
                crossfade(1000)

            }
        }
    }
}