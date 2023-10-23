package com.velosobr.petshopapp.presentation.petshopItemDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.velosobr.petshopapp.databinding.FragmentHomeItemsBinding
import com.velosobr.petshopapp.databinding.FragmentProductItemDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private lateinit var _binding: FragmentProductItemDetailsBinding
    private val binding: FragmentProductItemDetailsBinding get() = _binding

    private val viewModel: ProductItemDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentProductItemDetailsBinding.inflate(
        inflater,
        container,
        false
    ).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupProductDetail()

    }

    private fun setupProductDetail() {
//        TO DO: "Criar a chamada que começa a construção dos itens e recupera o item clicado")
    }
}