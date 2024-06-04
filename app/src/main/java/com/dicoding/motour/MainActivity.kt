package com.dicoding.motour

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.dicoding.motour.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

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

    private fun setupClickListener() {
        binding.ivScan.setOnClickListener {
            if (!checkPermission(CAMERA_PERMISSION)) {
                cameraPermissionLauncher.launch(CAMERA_PERMISSION)
            } else {
                performScan()
            }
        }
    }

    private fun performScan() {
        Intent(this, ScanActivity::class.java).run {
            startActivity(this)
        }
    }

    private fun showNeedCameraPermissionDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle(getString(R.string.camera))
            .setMessage(getString(R.string.need_camera_permission))
            .setPositiveButton(getString(R.string.close)) { _, _ ->
                closeOptionsMenu()
            }
            .show()
    }

    private fun checkPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    private val cameraPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            performScan()
        } else {
            showNeedCameraPermissionDialog()
        }
    }

    companion object {
        private const val CAMERA_PERMISSION = android.Manifest.permission.CAMERA
    }
}