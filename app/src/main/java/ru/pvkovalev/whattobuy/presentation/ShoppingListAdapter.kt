package ru.pvkovalev.whattobuy.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.pvkovalev.whattobuy.R
import ru.pvkovalev.whattobuy.domain.ShoppingItem

class ShoppingListAdapter :
    ListAdapter<ShoppingItem, ShoppingItemViewHolder>(ShoppingItemDiffCallback()) {

    var onShoppingItemLongClickListener: ((ShoppingItem) -> Unit)? = null
    var onShoppingItemClickListener: ((ShoppingItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingItemViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_DISABLED -> R.layout.shopping_item_disabled
            VIEW_TYPE_ENABLED -> R.layout.shopping_item_enabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ShoppingItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingItemViewHolder, position: Int) {
        val shoppingItem = getItem(position)
        holder.view.setOnLongClickListener {
            onShoppingItemLongClickListener?.invoke(shoppingItem)
            true
        }
        holder.view.setOnClickListener {
            onShoppingItemClickListener?.invoke(shoppingItem)
        }
        holder.tvName.text = shoppingItem.name
        holder.tvCount.text = shoppingItem.count.toString()
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.isActive) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }
    companion object {
        const val VIEW_TYPE_ENABLED = 100
        const val VIEW_TYPE_DISABLED = 101

        const val MAX_POOL_SIZE = 30
    }
}