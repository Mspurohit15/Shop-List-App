package com.example.shoplater.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="shopping_items")
data class ShoppingItem(
    //constructor
    @ColumnInfo(name="item_name")
    var name:String,
    @ColumnInfo(name="item_amount")
    var amount:Int
){
    //autoGenerate that basically means that room will do the work for us and generate all the id so we don't have to set them manually
    //which is also why we don't set in constructor
    @PrimaryKey(autoGenerate = true)
  var id:Int?=null
}
/*
we want to do is we want to create a class that represents a table in our
data base that class is called an entity

we want to make this class to data class so we write data keyword that basically
tells that  kotlin compiler that the main purpose of this class it to hold data so if
you don't know what an entity is just table of database

It's about this now the next thing we want to di is to tell room how we
want to access our database so we have to do we have t0 create an interface which is called a Dao that
DAO= Data Access Object and inside of that interface we declare the different method we need to access
our database



 */