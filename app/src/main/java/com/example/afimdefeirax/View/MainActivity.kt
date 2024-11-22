package com.example.afimdefeirax.View


import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.afimdefeirax.R
import com.example.afimdefeirax.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var toolbar: Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        setSupportActionBar(findViewById(R.id.toolbar));

        val navController = findNavController(R.id.nav_host_fragment_activity_main)


        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_mapfeiras, R.id.navigation_produtos, R.id.navigation_historico
            )
        )

        setupActionBarWithNavController(navController,appBarConfiguration)
        navView.setupWithNavController(navController)


        setupBar(navController)

    }

    private fun setupBar(navController: NavController){

        toolbar = binding.toolbar

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.navigation_produtos) {

                toolbar.inflateMenu(R.menu.menu_item)

            }
        }

    }

}