package com.example.todoapprecycler

import android.os.Bundle
import android.util.Log
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.todoapprecycler.room.AppDataBase
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity()
{

    private lateinit var stubContainer: LinearLayout
    private lateinit var fab : FloatingActionButton
    private lateinit var recyclerview : RecyclerView
    private lateinit var adapter : CustomAdapter
    private val todoLiveData: LiveData<List<ToDoItem>> by lazy { db.toDoDao().getAllItems() }

    private val db by lazy { Room.databaseBuilder(
        applicationContext,
        AppDataBase::class.java, "testDBOleg2")
        .allowMainThreadQueries()
        .build()}

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerview = findViewById(R.id.main_recycler_view)
        stubContainer = findViewById(R.id.main_no_items_container)
        fab = findViewById(R.id.main_fab)

        fab.setOnClickListener {
            val dialog = CustomDialog(this)
            dialog.show()
        }

        adapter = CustomAdapter(mutableListOf())

        recyclerview.layoutManager = LinearLayoutManager(this)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

        todoLiveData.observe(this, Observer{

            Log.e("AAAAAAA", "() -> $it")
            adapter.updateList(it) //todo
            if (it.isEmpty())
            {
                stubContainer.visibility = VISIBLE
                recyclerview.visibility = INVISIBLE
            }
            else
            {
                stubContainer.visibility = INVISIBLE
                recyclerview.visibility = VISIBLE
            }
        })
    }

    fun updateDB(item: ToDoItem)
    {
        stubContainer.visibility = INVISIBLE
        recyclerview.visibility = VISIBLE
        val noteDao = db.toDoDao().insertItem(item)
    }
}