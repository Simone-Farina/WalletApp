package com.example.testwallet

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        val fabButton = findViewById<FloatingActionButton>(R.id.fab)
        val navController = findNavController(R.id.nav_host_fragment)

        bottomNavigationView.setupWithNavController(navController)
        bottomNavigationView.background = null

        fabButton.setOnClickListener {
            // TODO: check if a better way exists
            if (navController.currentDestination?.id != R.id.TransactionFragment) {
                navController.navigate(R.id.TransactionFragment)
            }
        }

    }
}
