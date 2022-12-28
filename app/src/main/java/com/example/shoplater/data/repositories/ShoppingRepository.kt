package com.example.shoplater.data.repositories

import com.example.shoplater.data.db.ShoppingDatabase
import com.example.shoplater.data.db.entities.ShoppingItem

class ShoppingRepository(
    private val db:ShoppingDatabase
) {
   suspend   fun upsert(item:ShoppingItem)=db.getShoppingDao().upsert(item)
   suspend  fun delete(item:ShoppingItem)=db.getShoppingDao().delete(item)
     fun getAllShoppingItems()=db.getShoppingDao().getAppShoppingItems()

}
/*
This is will take as a arguments database an instance of our database class
Inside of this repository we want to implement the methods for our implement
basically our database method which we defined in shoppingDao object and inside
of that repository we want to call them basically and provide these methods later on
for the view model.
so view model can call the methods from the repository first will be our upsert method

That's so basically if for our repository because this is a very small project there aren't many functions
in it but it a good habit to implement apps in the MVVM Pattern

//I want to create the view model class for that we create another package

 */