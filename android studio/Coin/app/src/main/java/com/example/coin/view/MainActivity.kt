package com.example.coin.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.room.Room
import com.example.coin.R
import com.example.coin.databinding.ActivityMainBinding
import com.example.coin.repository.room.NotesDataBase


class MainActivity : AppCompatActivity()
{

    private val binding by lazy(LazyThreadSafetyMode.NONE) { ActivityMainBinding.inflate(layoutInflater) }
    private val bottomNavView by lazy(LazyThreadSafetyMode.NONE) { binding.bottomNavView }
    private val fab by lazy(LazyThreadSafetyMode.NONE) { binding.fabAddNote }
    private val roomDBNotes by lazy { Room.databaseBuilder(
        applicationContext,
        NotesDataBase::class.java, "roomDBNotes")
        .allowMainThreadQueries()
        .build()}

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_frame_activity_main) as NavHostFragment
        val navController = navHostFragment.navController
        bottomNavView.setupWithNavController(navController)

        fab.setOnClickListener {
            navController.navigate(R.id.add_note_fragment)
            bottomNavView.visibility = View.GONE
            it.visibility = View.GONE
        }

    }
    override fun onBackPressed()
    {
        bottomNavView.visibility = View.VISIBLE
        fab.visibility = View.VISIBLE
        super.onBackPressed()
    }

}
