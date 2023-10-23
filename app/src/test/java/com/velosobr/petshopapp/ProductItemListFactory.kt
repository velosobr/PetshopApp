package com.velosobr.petshopapp

import com.velosobr.petshopapp.domain.model.ProductItem

class ProductItemListFactory {
    fun create() = listOf(
        ProductItem(
            id = 1,
            description = "Escova para pet",
            weight = "500gr",
            quantity = 1,
            amount = "10,99",
            imageUrl = "https://www.petlove.com.br/images/products/231062/product/Escova_Furminator_New_C%C3%A3es_Pelo_Longo_2316474_2_G.jpg?1627741260"
        ),
        ProductItem(
            id = 2,
            description = "Tapete Higiênico Super Secão Citrus - 30 Unidades",
            weight = "200gr",
            quantity = 30,
            amount = "9,99",
            imageUrl = "https://www.petlove.com.br/images/products/260949/product/Tapete_Higi%C3%AAnico_Super_Sec%C3%A3o_Citrus_3104451_%281%29.jpg?1660325640"
        ),
        ProductItem(
            id = 3,
            description = "Petisco Pedigree Dentastix para Cães Adultos Porte Médio Sabor Carne",
            weight = "1kg",
            quantity = 1,
            amount = "20,99",
            imageUrl = "https://www.petlove.com.br/images/products/214121/product/Petisco_Pedigree_Dentastix_para_C%C3%A3es_Adultos_Ra%C3%A7as_M%C3%A9dias_-_7_Unidades_3104639-2_1.jpg?1627687506"
        ),
        ProductItem(
            id = 4,
            description = "Complexo Vitamínico Aminomix Pet",
            weight = "1kg",
            quantity = 1,
            amount = "120,99",
            imageUrl = "https://www.petlove.com.br/images/products/256039/product/7898053580470.png?1653509357"
        ),
        ProductItem(
            id = 5,
            description = "Cortador de Unha Guilhotina Médio",
            weight = "1kg",
            quantity = 1,
            amount = "59,99",
            imageUrl = "https://www.petlove.com.br/images/products/164111/product/7898904626302.jpg?1627551576"
        ),
        ProductItem(
            id = 6,
            description = "Antiparasitário Fenzol Pet Agener União com 6 unidades - 500 mg",
            weight = "100gr",
            quantity = 1,
            amount = "79,99",
            imageUrl = "https://www.petlove.com.br/images/products/232942/product/Antiparasit%C3%A1rio_Fenzol_Pet_Agener_Uni%C3%A3o_com_6_unidades_-_500_mg_31017361_2.jpg?1627747074"
        ),
        ProductItem(
            id = 7,
            description = "Ração Nutral Botia",
            weight = "200gr",
            quantity = 1,
            amount = "100,00",
            imageUrl = "https://www.petlove.com.br/images/products/191059/product/Botia_.jpg?1627615564"
        ),
        ProductItem(
            id = 8,
            description = "Ração Seca Pedigree para Cães Filhotes Raças Médias e Grandes",
            weight = "10.1kg",
            quantity = 1,
            amount = "100,00",
            imageUrl = "https://www.petlove.com.br/images/products/259806/product/Ra%C3%A7%C3%A3o_Pedigree_Carne_Frango_e_Cereais_C%C3%A3es_Filhotes_18_kg_2513333.png?1659451710"
        ),
        ProductItem(
            id = 9,
            description = "Ração Seca PremieR Pet Golden Salmão para Gatos Adultos Castrados",
            weight = "3.1kg",
            quantity = 1,
            amount = "200,00",
            imageUrl = "https://www.petlove.com.br/images/products/266053/product/Ra%C3%A7%C3%A3o_Seca_PremieR_Pet_Golden_Salm%C3%A3o_para_Gatos_Adultos_Castrados_-_10_1_Kg_31022435-3_1.jpg?1674076294"
        ),
        ProductItem(
            id = 10,
            description = "Conjunto Peitoral Style Mesh e Guia Rosa",
            weight = "500gr",
            quantity = 1,
            amount = "50,00",
            imageUrl = "https://www.petlove.com.br/images/products/235676/product/Kit_Style_meuMiAu_Unic%C3%B3rnio_Rosa_2637501.jpg?1627756265"
        )
    )
}