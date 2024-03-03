package com.example.coin.view

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.coin.R
import com.example.coin.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration


    private val mBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(
            layoutInflater
        )
    }
    private val mBottomNavView by lazy(LazyThreadSafetyMode.NONE) { mBinding.bottomNavView }
    private val mNavController by lazy(LazyThreadSafetyMode.NONE) {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_frame_activity_main) as NavHostFragment
        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        setSupportActionBar(mBinding.toolbar)

        val appBarConfiguration = AppBarConfiguration.Builder(
            setOf(
                R.id.bottom_nav_databoard,
                R.id.bottom_nav_add_note,
                R.id.bottom_nav_stats,
                R.id.bottom_nav_history
            )
        ).build()
        setupActionBarWithNavController(mNavController, appBarConfiguration)

        mBottomNavView.setOnNavigationItemSelectedListener {
            val navController = findNavController(R.id.fragment_frame_activity_main)
            navController.popBackStack(navController.graph.startDestinationId, false)
            navController.navigate(it.itemId)
            true
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.right_nav_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.fragment_frame_activity_main)
        when (item.itemId) {
            R.id.right_nav_settings -> {
                navController.navigate(R.id.right_nav_settings)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment_frame_activity_main)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}