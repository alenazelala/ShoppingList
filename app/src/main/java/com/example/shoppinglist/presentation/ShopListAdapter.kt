package com.example.shoppinglist.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.shoppinglist.R
import com.example.shoppinglist.domain.ShopItem

class ShopListAdapter :ListAdapter<ShopItem, ShopItemViewHolder>(ShopItemDifCallback()) {


    var onLongClickListener:((ShopItem) -> Unit)? = null
    var onShopItemClickListener:((ShopItem) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layout = when(viewType){
            VIEW_TYPE_ENABLED -> R.layout.item_shop_enabled
            VIEW_TYPE_DESENABLED -> R.layout.item_shop_desenabled
            else -> throw RuntimeException("Unknown viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent,
            false
        )
        return ShopItemViewHolder(view)
    }


    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if(item.enable) {
            VIEW_TYPE_ENABLED}
        else {
            VIEW_TYPE_DESENABLED}
    }

    override fun onBindViewHolder(viewHolder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)
        viewHolder.textViewName.text = shopItem.name
        viewHolder.textViewCount.text = shopItem.count.toString()
        viewHolder.view.setOnLongClickListener {
            onLongClickListener?.invoke(shopItem)
            true
        }
        viewHolder.view.setOnClickListener {
            onShopItemClickListener?.invoke(shopItem)
        }
    }
    companion object{
        const val VIEW_TYPE_ENABLED = 100
        const val VIEW_TYPE_DESENABLED = 101
        const val MAX_POOL_SIZE = 5
    }

}