package ru.pvkovalev.whattobuy.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.pvkovalev.whattobuy.data.ShoppingListRepositoryImpl
import ru.pvkovalev.whattobuy.domain.DeleteShoppingItemUseCase
import ru.pvkovalev.whattobuy.domain.EditShoppingItemUseCase
import ru.pvkovalev.whattobuy.domain.GetShoppingListUseCase
import ru.pvkovalev.whattobuy.domain.ShoppingItem

class MainViewModel : ViewModel() {

    val repository = ShoppingListRepositoryImpl

    val getShoppingListUseCase = GetShoppingListUseCase(repository)
    val deleteShoppingItemUseCase = DeleteShoppingItemUseCase(repository)
    val editShoppingItemUseCase = EditShoppingItemUseCase(repository)

    val shoppingList = getShoppingListUseCase.getShoppingList()

    fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        deleteShoppingItemUseCase.deleteShoppingItem(shoppingItem)
    }

    fun changeEnableState(shoppingItem: ShoppingItem) {
        val newItem = shoppingItem.copy(isActive = !shoppingItem.isActive)
        editShoppingItemUseCase.editShoppingItem(newItem)
    }
}