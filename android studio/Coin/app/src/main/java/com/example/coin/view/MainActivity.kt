package com.example.coin.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.onNavDestinationSelected
import com.example.coin.R
import com.example.coin.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(
            layoutInflater
        )
    }

    private val mBottomNavView by lazy(LazyThreadSafetyMode.NONE) { mBinding.bottomNavView }
    private val mRightNavView by lazy(LazyThreadSafetyMode.NONE) { mBinding.rightNavView }
    private val mNavController by lazy(LazyThreadSafetyMode.NONE) { val navHostFragment =
        supportFragmentManager.findFragmentById(R.id.fragment_frame_activity_main) as NavHostFragment
        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)

        mBottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_nav_more -> {
                    mBinding.drawerLayout.openDrawer(GravityCompat.END)
                    true
                }

                else -> {
                    item.onNavDestinationSelected(mNavController)
                    true
                }
            }
        }

        mRightNavView.setNavigationItemSelectedListener { menuItem ->
            menuItem.onNavDestinationSelected(mNavController)
            mBinding.drawerLayout.closeDrawer(GravityCompat.END)
            true
        }

    }

}