package com.kot.tainex

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.kot.tainex.databinding.MainPageBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : MainPageBinding
    private lateinit var appBarConfig : AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        val drawerLayout : DrawerLayout = binding.drawerLayout
        val navView : NavigationView = binding.navView
        val navController = findNavController(R.id.main_nav_fragment)

        appBarConfig = AppBarConfiguration(
            setOf(R.id.mainPage,R.id.aboutPage),drawerLayout
        )
        setupActionBarWithNavController(navController,appBarConfig)
        navView.setupWithNavController(navController)
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.main_nav_fragment)
        return navController.navigateUp(appBarConfig) || super.onSupportNavigateUp()
    }
}