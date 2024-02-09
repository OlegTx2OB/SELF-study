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
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(
            layoutInflater
        )
    }

    private val bottomNavView by lazy(LazyThreadSafetyMode.NONE) { binding.bottomNavView }
    private val rightNavView by lazy(LazyThreadSafetyMode.NONE) { binding.rightNavView }
    private val navController by lazy(LazyThreadSafetyMode.NONE) {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_frame_activity_main) as NavHostFragment
        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_nav_more -> {
                    binding.drawerLayout.openDrawer(GravityCompat.END)
                    true
                }

                else -> {
                    item.onNavDestinationSelected(navController)
                    true
                }
            }
        }

        rightNavView.setNavigationItemSelectedListener { menuItem ->
            menuItem.onNavDestinationSelected(navController)
            binding.drawerLayout.closeDrawer(GravityCompat.END)
            true
        }

    }

}