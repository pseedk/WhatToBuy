package ru.pvkovalev.whattobuy.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DiffUtil.DiffResult
import androidx.recyclerview.widget.RecyclerView
import ru.pvkovalev.whattobuy.R
import ru.pvkovalev.whattobuy.domain.ShoppingItem

class ShoppingListAdapter : RecyclerView.Adapter<ShoppingListAdapter.ShoppingItemViewHolder>() {

    var count = 0

    var shoppingList = listOf<ShoppingItem>()
        set(value) {
            val callback = ShopListDiffCallback(shoppingList, value)
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }

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
        Log.d("ShoppingListAdapter", "onBindViewHolder ${++count}")
        val shoppingItem = shoppingList[position]
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

    override fun getItemCount(): Int = shoppingList.size


    override fun getItemViewType(position: Int): Int {
        val item = shoppingList[position]
        return if (item.isActive) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }

    class ShoppingItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvCount = view.findViewById<TextView>(R.id.tv_count)
    }


    companion object {
        const val VIEW_TYPE_ENABLED = 100
        const val VIEW_TYPE_DISABLED = 101

        const val MAX_POOL_SIZE = 15
    }
}