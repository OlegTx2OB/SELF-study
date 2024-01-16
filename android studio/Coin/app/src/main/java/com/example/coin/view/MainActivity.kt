package com.example.coin.view

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.coin.R
import com.example.coin.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity()
{

    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNavView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNavView = binding.bottomNavView

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_frame_activity_main) as NavHostFragment
        val navController = navHostFragment.navController
        bottomNavView.setupWithNavController(navController)

        val fabAddNote: FloatingActionButton = binding.fabAddNote
        fabAddNote.setOnClickListener {
            bottomNavView.visibility = View.GONE
            findViewById<FloatingActionButton>(R.id.fab_add_note).visibility = View.GONE
            navController.navigate(R.id.add_note_fragment)
        }

    }
    override fun onBackPressed()
    {
        bottomNavView.visibility = View.VISIBLE
        findViewById<FloatingActionButton>(R.id.fab_add_note).visibility = View.VISIBLE

        super.onBackPressed()
    }

}