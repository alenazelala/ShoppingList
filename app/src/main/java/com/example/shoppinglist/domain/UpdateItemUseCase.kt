package com.example.shoppinglist.domain

class UpdateItemUseCase( private val shopListRepository: ShopListRepository) {

    fun UpdateItem(shopItem: ShopItem){
       shopListRepository.updateItem(shopItem)
    }
}