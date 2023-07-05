package ru.pvkovalev.whattobuy.domain

class GetShoppingListUseCase(private val shoppingListRepository: ShoppingListRepository) {

    fun getShoppingList(): List<ShoppingItem> {
        return shoppingListRepository.getShoppingList()
    }
}