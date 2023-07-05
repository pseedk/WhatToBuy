package ru.pvkovalev.whattobuy.domain

class AddShoppingItemUseCase(private val shoppingListRepository: ShoppingListRepository) {

    fun addShoppingItem(shoppingItem: ShoppingItem) {
        shoppingListRepository.addShoppingItem(shoppingItem)
    }
}