@file:Suppress("DEPRECATION")

package com.example.shoplater.ui.shoppinglist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoplater.R
import com.example.shoplater.data.db.ShoppingDatabase
import com.example.shoplater.data.db.entities.ShoppingItem
import com.example.shoplater.data.repositories.ShoppingRepository
import com.example.shoplater.other.ShoppingItemAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


@Suppress("DEPRECATION")
class ShoppingActivity : AppCompatActivity(),KodeinAware {
    override val kodein by kodein()
    private val factory:ShoppingViewModelFactory by instance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)


        val viewModel= ViewModelProviders.of(this,factory)[ShoppingViewModel::class.java]

       val adapter=ShoppingItemAdapter(listOf(),viewModel)
       val rvShoppingItems=findViewById<RecyclerView>(R.id.rvShoppingItems)
       rvShoppingItems.layoutManager=LinearLayoutManager(this)
       rvShoppingItems.adapter=adapter
        viewModel.getAllShoppingItems().observe(this, Observer {
            adapter.items=it
            adapter.notifyDataSetChanged()
        })
        val fab=findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
           AddShoppingItemDialog(this,
           object :AddDialogListener{
               override fun onAddButtonClicked(item: ShoppingItem) {
                   viewModel.upsert(item)
               }
           }).show()
        }
    }
}




/*
What is a design pattern and why should we use it ?

so the intention of design patterns is to do have a reusable solutions  for common problems and
designing software and it basically describes the relationship between your classes your interfaces
and objects  and every time  you want to make a bigger and more complex project it would be really
confusing to put all your code in activities so that is why we want to have a consistent pattern
that helps us to organize our code and also make it on available  for other developers


Basic Architecture of the MVVM pattern
-->First of all our activity of fragment is responsible for holding all our UI elements and views but
   every of our activities and fragments also holds an instance of a view model
-->View Model is basically a class that  provides the data for a specific UI component  and it responsible for
   for calling other components which to load the data and forward it to our activity of fragment the view model does
   not know anything about our UI components like our views because they are handled in the activity of fragment class
   inside the view model box you probably already noticed a little Livedata box Live data the belongs to the
   Android Architectural components which is an observable data hold a class that is lifecycle aware which is really
   awesome because lifecycle awareness means that  if only updates observers that are in in an active lifecycle state
   so normally when you rotate the device the activity is recreated so uncreate is called agiain which resets all your UI
   components but with Live Data this won't happen because the view model is not activity class so it does not have any own
   life cycle which mean  that every time our activity is recreated it will immediately receive
   the current data from the view model
-->Never the less the view model does not get its data directly from the database instead it gets it from the repository as you can see here
   the repository is basically another class we have to implement in our project that  fetches the data from the model and our remote data source
   which is basically a web service.

   The model in basically in our case just the database class which implements the room database and the
   repository will get the data from our database class

 */