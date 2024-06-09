package com.dicoding.motour.presentation.scanner

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.dicoding.motour.databinding.ActivityPostScanBinding
import com.dicoding.motour.presentation.scanner.ScanActivity

class PostScanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostScanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()

        super.onCreate(savedInstanceState)
        binding = ActivityPostScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupEdgeToEdge()
        setupClickListener()
        setupResult()
    }

    private fun setupEdgeToEdge() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.btnBack) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(0, systemBars.top, 0, 0)
            insets
        }
    }

    private fun setupClickListener() {
        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setupResult() {
        val scanCode = intent.getIntExtra(ScanActivity.EXTRA_SCAN_STATUS_CODE, -1)
        val imageUri = intent.getStringExtra(ScanActivity.EXTRA_SCAN_URI)

        when (scanCode) {
            ScanActivity.SCAN_SUCCESS -> {
                Glide.with(binding.root)
                    .load(imageUri)
                    .into(binding.ivLandmark)
            }

            ScanActivity.SCAN_FAILED_PREDICTION -> {
                Toast.makeText(this, "Failed to predict", Toast.LENGTH_SHORT).show()
            }
        }
    }
}