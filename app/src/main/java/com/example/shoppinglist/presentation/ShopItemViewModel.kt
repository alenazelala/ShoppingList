package com.example.shoppinglist.presentation




import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglist.data.ShopListRepositoryImpl
import com.example.shoppinglist.domain.AddShopItemUseCase
import com.example.shoppinglist.domain.GetShopItemUseCase
import com.example.shoppinglist.domain.ShopItem
import com.example.shoppinglist.domain.UpdateItemUseCase



class ShopItemViewModel:ViewModel() {
    private val repository = ShopListRepositoryImpl

    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val updateItemUseCase = UpdateItemUseCase(repository)
    private val getShopItemUseCase = GetShopItemUseCase(repository)


     private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean> = _errorInputName

    private val _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean> = _errorInputCount

    private val _shopItem = MutableLiveData<ShopItem>()
        val shopItem: LiveData<ShopItem> = _shopItem

    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit> = _shouldCloseScreen


    fun addShopItem(inputName: String?, inputCount: String?){
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldValid = validateInput(name, count)
        if(fieldValid) {
            var shopItem = ShopItem(name, count,true)
            addShopItemUseCase.addShopItem(shopItem)
            finishWork()
        }
    }

    fun updateShopItem(inputName: String?, inputCount: String?){
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldValid = validateInput(name, count)
        if(fieldValid){
             _shopItem.value?.let {
                 val item = it.copy(name = name, count = count)
                 updateItemUseCase.UpdateItem(item)
                 finishWork()
             }


        }

    }
    fun getShopItem(shopItemId: Int){
        val item = getShopItemUseCase.getShopItem(shopItemId)
        _shopItem.value = item
    }
    private fun parseName(inputName: String?):String{
        return inputName?.trim() ?: ""
    }
    private fun parseCount(inputCount: String?):Int{
        return try {
            inputCount?.trim()?.toInt() ?: 0

        }catch (e: Exception){
            0
        }
    }
    private fun validateInput(name: String, count: Int): Boolean{
        var result = true
        if(name.isBlank()) {
            _errorInputName.value = true
            result = false}
        if (count<=0) {
            _errorInputCount.value = true
            result = false
           }
        return  result
    }
    fun resetErrorInputName(){
        _errorInputName.value = false
    }
    fun resetErrorInputCount(){
        _errorInputCount.value = false
    }
    private fun finishWork(){
        _shouldCloseScreen.value = Unit
    }
}
