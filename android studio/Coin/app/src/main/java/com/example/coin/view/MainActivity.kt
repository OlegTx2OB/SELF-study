package com.example.coin.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.onNavDestinationSelected
import com.example.coin.R
import com.example.coin.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(
            layoutInflater
        )
    }
    private val bottomNavView by lazy(LazyThreadSafetyMode.NONE) { binding.bottomNavView }
    private val fab by lazy(LazyThreadSafetyMode.NONE) { binding.fabAddNote }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_frame_activity_main) as NavHostFragment
        val navController = navHostFragment.navController

        bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_nav_more -> {
                    val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
                    drawerLayout.openDrawer(GravityCompat.END)
                    true
                }

                else -> {
                    item.onNavDestinationSelected(navController)
                }
            }
        }


        fab.setOnClickListener {
            navController.navigate(R.id.add_note_fragment)
            bottomNavView.visibility = View.GONE
            it.visibility = View.GONE
        }

    }

    override fun onBackPressed() {
        bottomNavView.visibility = View.VISIBLE
        fab.visibility = View.VISIBLE
        super.onBackPressed()
    }

}