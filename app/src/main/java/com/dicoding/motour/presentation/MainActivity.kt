package com.dicoding.motour.presentation

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.dicoding.motour.R
import com.dicoding.motour.data.preferences.LanguageUtil
import com.dicoding.motour.databinding.ActivityMainBinding
import com.dicoding.motour.presentation.di.Injector
import com.dicoding.motour.presentation.scanner.ScanActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: MainViewModelFactory
    private lateinit var viewModel: MainViewModel

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        enableEdgeToEdge()

        (applicationContext as Injector).createStartupSubComponent().inject(this)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        viewModel.getPreferredLanguage().observe(this) { language ->
            val appLocale = LanguageUtil.getLanguageTag(language)
            runBlocking {
                AppCompatDelegate.setApplicationLocales(appLocale)
            }
        }

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