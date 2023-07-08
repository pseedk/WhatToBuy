package ru.pvkovalev.whattobuy.domain

import androidx.lifecycle.LiveData

class GetShoppingListUseCase(private val shoppingListRepository: ShoppingListRepository) {

    fun getShoppingList(): LiveData<List<ShoppingItem>> {
        return shoppingListRepository.getShoppingList()
    }
}