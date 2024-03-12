package com.example.shoppinglist.domain

data class ShopItem(
    val name: String,
    val count: Int,
    val enable: Boolean,
    var id: Int = UNDEFIND_ID
){
    companion object{
        const val UNDEFIND_ID = -1
    }
}
