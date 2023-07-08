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

    val shoppingList = MutableLiveData<List<ShoppingItem>>()

    fun getShoppingList() {
        val list = getShoppingListUseCase.getShoppingList()
        shoppingList.value = list
    }

    fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        deleteShoppingItemUseCase.deleteShoppingItem(shoppingItem)
        getShoppingList()
    }

    fun changeEnableState(shoppingItem: ShoppingItem) {
        val newItem = shoppingItem.copy(isActive = !shoppingItem.isActive)
        editShoppingItemUseCase.editShoppingItem(newItem)
        getShoppingList()
    }
}