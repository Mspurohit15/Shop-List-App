package com.example.shoplater.ui.shoppinglist

import com.example.shoplater.data.db.entities.ShoppingItem

interface AddDialogListener {

    fun onAddButtonClicked(item: ShoppingItem){
    }


}