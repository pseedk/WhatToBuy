package ru.pvkovalev.whattobuy.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.pvkovalev.whattobuy.data.ShoppingListRepositoryImpl
import ru.pvkovalev.whattobuy.domain.AddShoppingItemUseCase
import ru.pvkovalev.whattobuy.domain.EditShoppingItemUseCase
import ru.pvkovalev.whattobuy.domain.GetShoppingItemUseCase
import ru.pvkovalev.whattobuy.domain.ShoppingItem
import java.lang.Exception

class ShoppingItemViewModel : ViewModel() {

    val repository = ShoppingListRepositoryImpl

    val getShoppingItemUseCase = GetShoppingItemUseCase(repository)
    val addShoppingItemUseCase = AddShoppingItemUseCase(repository)
    val editShoppingItemUseCase = EditShoppingItemUseCase(repository)

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean>
        get() = _errorInputCount

    private val _shoppingItem = MutableLiveData<ShoppingItem>()
    val shoppingItem: LiveData<ShoppingItem>
    get() = _shoppingItem

    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit>
        get() = _shouldCloseScreen

    fun getShoppingItem(shoppingItemId: Int) {
        val item = getShoppingItemUseCase.getShoppingItem(shoppingItemId)
        _shoppingItem.value = item
    }

    fun addShoppingItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if (fieldsValid) {
            val shoppingItem = ShoppingItem(name, count, true)
            addShoppingItemUseCase.addShoppingItem(shoppingItem)
            finishWork()
        }
    }

    fun editShoppingItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if (fieldsValid) {
            _shoppingItem.value?.let {
                val item = it.copy(name = name, count = count)
                editShoppingItemUseCase.editShoppingItem(item)
                finishWork()
            }
        }
    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    private fun parseCount(inputCount: String?): Int {
        return try {
            inputCount?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun validateInput(name: String, count: Int): Boolean {
        var result = true
        if (name.isBlank()) {
            _errorInputName.value = true
            result = false
        }
        if (count <= 0) {
            _errorInputCount.value = true
            result = false
        }
        return result
    }

    fun resetErrorInputName() {
        _errorInputName.value = false
    }

    fun resetErrorInputCount() {
        _errorInputCount.value = false
    }

    private fun finishWork() {
        _shouldCloseScreen.value = Unit
    }

}