package com.store.model

data class Product(
    var id: Int = 0,
    val name: String? = "",
    val type: String? = "",
    val inventory: Int? = 0,
    val cost: Int? = 0
)
