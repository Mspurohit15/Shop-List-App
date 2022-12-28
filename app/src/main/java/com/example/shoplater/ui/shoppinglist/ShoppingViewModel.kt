package com.example.shoplater.ui.shoppinglist

import androidx.lifecycle.ViewModel
import com.example.shoplater.data.db.entities.ShoppingItem
import com.example.shoplater.data.repositories.ShoppingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingViewModel(
    private val repository:ShoppingRepository
    ):ViewModel() {
  /*
  Inside that view model class we basically insert our functions which we defined
  in a repository before because inside the view model we want to actually call  them so
  we write function upsert this is not a suspend function now because inside
  of that function we want to actually use a coroutine to execute the code of our
  upsert functions
   */
    fun upsert(item: ShoppingItem)= CoroutineScope(Dispatchers.Main).launch {
       repository.upsert(item)
   }
    fun delete(item: ShoppingItem)= CoroutineScope(Dispatchers.Main).launch {
        repository.delete(item)
    }
    fun getAllShoppingItems()=repository.getAllShoppingItems()

    //Create an instance of our shopping view model inside of our shoppingActivity but first i noticed that our shopping list package is empty because i forget

}