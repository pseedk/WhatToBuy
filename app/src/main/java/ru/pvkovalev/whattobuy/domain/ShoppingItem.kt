package ru.pvkovalev.whattobuy.domain

data class ShoppingItem(
    val name: String,
    val count: Int,
    val isActive: Boolean,
    var id: Int = UNDEFINED_ID
) {
    companion object {
        const val UNDEFINED_ID = -1
    }
}

