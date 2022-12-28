package com.example.shoplater.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shoplater.data.db.entities.ShoppingItem

@Dao
interface ShoppingDao {

    @Insert(onConflict =OnConflictStrategy.REPLACE)
    fun upsert(item: ShoppingItem)

    @Delete
    fun delete(item: ShoppingItem)

    @Query(value = "SELECT *FROM SHOPPING_ITEMS")
    fun getAppShoppingItems():LiveData<List<ShoppingItem>>

}


/*
It's about this now the next thing we want to di is to tell room how we
want to access our database so we have to do we have t0 create an interface which is called a Dao that
DAO= Data Access Object and inside of that interface we declare the different method we need to access
our database

ex : Insert, Delete and save etc ..

SO sql is don't allow to write to the databases in the main thread so we have to do we
have to call these functions  asynchronously which basically means that we have to use
either a threat or in kotlin we can use coroutines and to mark this function as function that can be
executed as in asynchronously . We write the suspend  keyword before that function keyword also


Next we want to create a class the represent our actual database
 */