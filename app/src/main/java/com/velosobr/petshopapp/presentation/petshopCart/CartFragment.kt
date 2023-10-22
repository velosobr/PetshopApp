package com.velosobr.petshopapp.presentation.petshopCart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.velosobr.petshopapp.databinding.FragmentCartBinding
import com.velosobr.petshopapp.databinding.FragmentHomeItemsBinding
import com.velosobr.petshopapp.presentation.petshopHomeItems.HomeItemsAdapter
import com.velosobr.petshopapp.presentation.petshopHomeItems.HomeItemsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {

    private lateinit var _binding: FragmentCartBinding
    private val binding: FragmentCartBinding get() = _binding

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

    }
}