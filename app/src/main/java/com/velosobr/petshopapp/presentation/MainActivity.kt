package com.velosobr.petshopapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.velosobr.petshopapp.R
import com.velosobr.petshopapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_container) as NavHostFragment

        navController = navHostFragment.navController

//        appBarConfiguration = AppBarConfiguration(
//            setOf(R.id.homeItemsFragment, R.id.detailsFragment, R.id.cartFragment, )
//        )

//        binding.toolbarApp.setupWithNavController(navController, appBarConfiguration)
//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            val isTopLevelDestination =
//                appBarConfiguration.topLevelDestinations.contains(destination.id)
//            if (!isTopLevelDestination) {
//                binding.toolbarApp.setNavigationIcon(R.drawable.ic_back)
//            }
//        }
    }
}