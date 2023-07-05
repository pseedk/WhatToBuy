package ru.pvkovalev.whattobuy.domain

class DeleteShoppingItemUseCase(private val shoppingListRepository: ShoppingListRepository) {

    fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingListRepository.deleteShoppingItem(shoppingItem)
    }
}