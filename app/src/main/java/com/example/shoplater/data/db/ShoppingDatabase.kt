package com.example.shoplater.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shoplater.data.db.entities.ShoppingItem


@Database(
    entities = [ShoppingItem::class], //we use array because it can more entities
    version = 1,
    exportSchema = false
)
//Inherit From room database
abstract class ShoppingDatabase :RoomDatabase() {
    //Need to declare a functions that refers to our shopping Dao object
    //With that function we just make sure that we can access our database operations from inside the database
    abstract fun getShoppingDao(): ShoppingDao
    //Next we have to create a companion object which is basically what the static keyword in java means
    companion object{
        /*
        basically means that rights to this instance will be made visible instantly to other threats we
        need to do that because we really want to make sure that only thread at time is writing to that
        instance because otherwise it could be that there are two thread and both want to initialize
        that instance variable at the same time and then we would have two instances  of the same
        shopping database which  we don't
        */
        @Volatile
        /*
         we have to create instance of shopping database inside of that class which is called a singleton
          because it would make any sense to allow it to create multiple instance of the same database
          at the same time
         */
        private var instance: ShoppingDatabase?=null
        private val LOCK=Any()
        //Now we want to create an operator function invoke ,operate a function especially  in combination with invoke basically means that this  function is executed whenever we create an instance our our shopping database whenever we write something like this ShoppingDatabase()them it's run

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also { instance =it }
        }



        //Now we want to write a method that is used to instantiate our actual database

        private fun createDatabase(context :Context)=
            Room.databaseBuilder(
                context.applicationContext,
                ShoppingDatabase::class.java,
                "ShoppingDB.db"
            ).build()

    }


}


/*
Next we want to create a class the represent our actual database

 */