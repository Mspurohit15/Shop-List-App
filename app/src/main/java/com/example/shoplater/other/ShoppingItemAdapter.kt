package com.example.shoplater.other

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoplater.R
import com.example.shoplater.data.db.entities.ShoppingItem
import com.example.shoplater.ui.shoppinglist.ShoppingViewModel

class ShoppingItemAdapter(
    var items: List<ShoppingItem>,
    private val viewModel:ShoppingViewModel
):RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
     val view=LayoutInflater.from(parent.context).inflate(R.layout.shopping_item,parent,false)
        return ShoppingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {

        val curShoppingItem=items[position]

        val tvName=holder.itemView.findViewById<TextView>(R.id.tvName)
        val tvAmount=holder.itemView.findViewById<TextView>(R.id.tvAmount)
        tvName.text=curShoppingItem.name
        tvAmount.text="${curShoppingItem.amount}"

        val ivDelete=holder.itemView.findViewById<ImageView>(R.id.ivDelete)
        ivDelete.setOnClickListener {
           viewModel.delete(curShoppingItem)
        }
        val ivMinus=holder.itemView.findViewById<ImageView>(R.id.ivMinus)
        ivMinus.setOnClickListener {
            if(curShoppingItem.amount>0){
                curShoppingItem.amount--
                viewModel.upsert(curShoppingItem)
            }

        }
        val ivPlus=holder.itemView.findViewById<ImageView>(R.id.tvPlus)
        ivPlus.setOnClickListener {
            curShoppingItem.amount++
            viewModel.upsert(curShoppingItem)
        }



    }

    override fun getItemCount(): Int {
        return items.size
    }


    inner class ShoppingViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)
}