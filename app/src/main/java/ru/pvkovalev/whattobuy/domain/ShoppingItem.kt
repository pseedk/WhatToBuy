package ru.pvkovalev.whattobuy.domain

data class ShoppingItem(
    val id: Int,
    val name: String,
    val count: Int,
    val isActive: Boolean
)
