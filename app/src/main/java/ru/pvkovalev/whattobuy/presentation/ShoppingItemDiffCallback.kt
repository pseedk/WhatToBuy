package ru.pvkovalev.whattobuy.presentation

import androidx.recyclerview.widget.DiffUtil
import ru.pvkovalev.whattobuy.domain.ShoppingItem

class ShoppingItemDiffCallback : DiffUtil.ItemCallback<ShoppingItem>() {

    override fun areItemsTheSame(oldItem: ShoppingItem, newItem: ShoppingItem) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: ShoppingItem, newItem: ShoppingItem) =
        oldItem == newItem
}