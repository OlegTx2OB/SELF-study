package com.example.coin.view

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.coin.R
import com.example.coin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.bottomNavView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)

//        val bottomNavView: BottomNavigationView = findViewById(R.id.bottom_nav_view)
//        bottomNavView.setOnNavigationItemSelectedListener { item ->
//            when(item.itemId)
//            {
//                R.id.bottom_nav_more -> {
//                    val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
//                    drawerLayout.openDrawer(GravityCompat.END)
//                    true
//                }
//                else -> true
//            }
//        }

    }

}