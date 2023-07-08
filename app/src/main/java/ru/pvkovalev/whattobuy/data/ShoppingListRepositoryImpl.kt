package ru.pvkovalev.whattobuy.data

import ru.pvkovalev.whattobuy.domain.ShoppingItem
import ru.pvkovalev.whattobuy.domain.ShoppingListRepository
import java.lang.RuntimeException

object ShoppingListRepositoryImpl : ShoppingListRepository {

    private val shoppingList = mutableListOf<ShoppingItem>()

    private var autoincrementId = 0

    init {
        for (i in 0 until 10) {
            val item = ShoppingItem("Name $i", i, true)
            addShoppingItem(item)
        }
    }

    override fun addShoppingItem(shoppingItem: ShoppingItem) {
        if (shoppingItem.id == ShoppingItem.UNDEFINED_ID) {
            shoppingItem.id = autoincrementId++
        }
        shoppingList.add(shoppingItem)
    }

    override fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingList.remove(shoppingItem)
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

    override fun getShoppingList(): List<ShoppingItem> {
        return shoppingList.toList()
    }
}