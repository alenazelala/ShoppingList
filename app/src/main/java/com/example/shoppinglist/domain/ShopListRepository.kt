package com.example.shoppinglist.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {
    fun addShopItem(shopItem: ShopItem)
    fun deleteItem(shopItem: ShopItem)
    fun updateItem(shopItem: ShopItem)
    fun getShopItem(shopItemId: Int) : ShopItem
    fun getShopList():LiveData<List<ShopItem>>
}