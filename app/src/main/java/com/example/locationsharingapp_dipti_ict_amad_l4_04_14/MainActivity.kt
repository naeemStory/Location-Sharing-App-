package com.example.locationsharingapp_dipti_ict_amad_l4_04_14

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.locationsharingapp_dipti_ict_amad_l4_04_14.databinding.ActivityMainBinding
import com.example.locationsharingapp_dipti_ict_amad_l4_04_14.viewdipti14.LoginActivityDipti14
import com.google.firebase.Firebase
import com.google.firebase.auth.auth


class MainActivity : AppCompatActivity() {

    lateinit var actionDrawerToggle: ActionBarDrawerToggle

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        var navController = findNavController(R.id.fragmentContainerView)
        binding.bottomBar.setupWithNavController(navController)
        binding.drawerNav.setupWithNavController(navController)
        actionDrawerToggle =
            ActionBarDrawerToggle(this, binding.drawerlayout, R.string.nav_open, R.string.nav_close)
        actionDrawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.drawerNav.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.lagout -> {
                    Firebase.auth.signOut()
                    startActivity(Intent(this, LoginActivityDipti14::class.java))
                    finish()

                }
                R.id.profile -> {
                    navController.navigate(R.id.profileFragment)
                }
                R.id.friends -> {
                    navController.navigate(R.id.friendsFragment)
                }

            }
            true
        }
        binding.bottomBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.lagout-> {
                    Firebase.auth.signOut()
                    startActivity(Intent(this, LoginActivityDipti14::class.java))
                    finish()

                }
                R.id.profile -> {
                    navController.navigate(R.id.profileFragment)
                }
                R.id.friends -> {
                    navController.navigate(R.id.friendsFragment)
                }

            }
            true

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionDrawerToggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }
}