package com.example.shoplater.ui.shoppinglist

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

import androidx.appcompat.app.AppCompatDialog
import com.example.shoplater.R
import com.example.shoplater.data.db.ShoppingDatabase
import com.example.shoplater.data.db.entities.ShoppingItem
import com.example.shoplater.data.repositories.ShoppingRepository

class AddShoppingItemDialog(
    context: Context,
    private val addDialogListener: AddDialogListener
    ):AppCompatDialog(context) {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_shopping_item)

        val tvAdd=findViewById<TextView>(R.id.tvAdd)
        val etName=findViewById<EditText>(R.id.etName)
        val etAmount=findViewById<EditText>(R.id.etAmount)
        val tvCancel=findViewById<TextView>(R.id.tvCancel)

        tvAdd?.setOnClickListener {
         val name = etName?.text.toString()
         val amount = etAmount?.text.toString()
            if(name.isEmpty() || amount.isEmpty()){
                Toast.makeText(context,"Please enter all the information",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val item=ShoppingItem(name, amount.toInt())
            addDialogListener.onAddButtonClicked(item)
            dismiss()

        }
        tvCancel?.setOnClickListener {
            cancel()
        }


    }
}