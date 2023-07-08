package ru.pvkovalev.whattobuy.domain

import androidx.lifecycle.LiveData

interface ShoppingListRepository {

    fun addShoppingItem(shoppingItem: ShoppingItem)

    fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun editShoppingItem(shoppingItem: ShoppingItem)

    fun getShoppingItem(shoppingItemId: Int): ShoppingItem

    fun getShoppingList(): LiveData<List<ShoppingItem>>
}