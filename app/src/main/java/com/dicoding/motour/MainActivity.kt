package com.dicoding.motour

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.dicoding.motour.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListener()

        val host =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navController = host.navController

        binding.bottomNav.setupWithNavController(navController)
        binding.bottomNav.menu.getItem(1).isEnabled = false
        binding.bottomNav.itemIconTintList = null
    }

    private fun setupEdgeToEdge() {
        // TBD: Insets stuff
    }

    private fun setupClickListener() {
        binding.ivScan.setOnClickListener {
            Intent(this, ScanActivity::class.java).run {
                startActivity(this)
            }
        }
    }
}