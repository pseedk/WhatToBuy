package ru.pvkovalev.whattobuy.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.pvkovalev.whattobuy.domain.ShoppingItem
import ru.pvkovalev.whattobuy.domain.ShoppingListRepository
import kotlin.random.Random

object ShoppingListRepositoryImpl : ShoppingListRepository {

    private val shoppingListLiveData = MutableLiveData<List<ShoppingItem>>()
    private val shoppingList = mutableListOf<ShoppingItem>()

    private var autoincrementId = 0

    init {
        for (i in 0 until 1000) {
            val item = ShoppingItem("Name $i", i, Random.nextBoolean())
            addShoppingItem(item)
        }
    }

    override fun addShoppingItem(shoppingItem: ShoppingItem) {
        if (shoppingItem.id == ShoppingItem.UNDEFINED_ID) {
            shoppingItem.id = autoincrementId++
        }
        shoppingList.add(shoppingItem)
        updateList()
    }

    override fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingList.remove(shoppingItem)
        updateList()
    }

    override fun editShoppingItem(shoppingItem: ShoppingItem) {
        val oldElement = getShoppingItem(shoppingItem.id)
        shoppingList.remove(oldElement)
        addShoppingItem(shoppingItem)
    }

    override fun getShoppingItem(shoppingItemId: Int): ShoppingItem {
        return shoppingList.find {
            it.id == shoppingItemId
        } ?: throw RuntimeException("Element with id $shoppingItemId not found")
    }

    override fun getShoppingList(): LiveData<List<ShoppingItem>> {
        return shoppingListLiveData
    }

    private fun updateList() {
        shoppingListLiveData.value = shoppingList.toList()
    }
}