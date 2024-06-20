package com.dicoding.motour.presentation.scanner

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.dicoding.motour.databinding.ActivityResultScanBinding
import com.dicoding.motour.presentation.landmark.LandmarkDetailActivity
import com.dicoding.motour.utils.formatName
import com.dicoding.motour.utils.parseAndFormatDate

class ResultScanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultScanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityResultScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupEdgeToEdge()
        setupResult()
        setupAction()
    }

    private fun setupAction() {
        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setupResult() {
        val scanCode = intent.getStringExtra(EXTRA_SCAN_CODE)

        when (scanCode) {
            "201" -> {
                val scanId = intent.getIntExtra(EXTRA_SCAN_ID, 1)
                val scanResult = formatName(intent.getStringExtra(EXTRA_SCAN_RESULT).toString())
                val scanRate = intent.getStringExtra(EXTRA_SCAN_RATE)
                val scanImage = intent.getStringExtra(EXTRA_SCAN_IMAGE)
                val scanCreatedAt =
                    parseAndFormatDate(intent.getStringExtra(EXTRA_SCAN_CREATED_AT).toString())
                showError(false)
                binding.tvNameResult.text = scanResult
                binding.tvRate.text = scanRate.toString() + " %"
                binding.tvCreatedAt.text = scanCreatedAt
                Glide.with(this)
                    .load(scanImage)
                    .into(binding.ivLandmark)
                binding.btnFurthermore.setOnClickListener {
                    val intent = Intent(this, LandmarkDetailActivity::class.java)
                    intent.putExtra(LandmarkDetailActivity.EXTRA_LANDMARK_ID, scanId)
                    startActivity(intent)
                    finish()
                }
            }

            "400" -> {
                showError(true)
                binding.btnRetry.setOnClickListener {
                    val intent = Intent(this, ScanActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    private fun showError(state: Boolean) {
        binding.ivError.visibility = if (state) View.VISIBLE else View.GONE
        binding.btnRetry.visibility = if (state) View.VISIBLE else View.GONE
        binding.ivLandmark.visibility = if (state) View.GONE else View.VISIBLE
        binding.tvNameResult.visibility = if (state) View.GONE else View.VISIBLE
        binding.tvRate.visibility = if (state) View.GONE else View.VISIBLE
        binding.tvCreatedAt.visibility = if (state) View.GONE else View.VISIBLE
    }

    private fun setupEdgeToEdge() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    companion object {
        const val EXTRA_SCAN_ID = "EXTRA_SCAN_ID"
        const val EXTRA_SCAN_RESULT = "EXTRA_SCAN_RESULT"
        const val EXTRA_SCAN_RATE = "EXTRA_SCAN_RATE"
        const val EXTRA_SCAN_IMAGE = "EXTRA_SCAN_IMAGE"
        const val EXTRA_SCAN_CREATED_AT = "EXTRA_SCAN_CREATED_AT"
        const val EXTRA_SCAN_CODE = "EXTRA_SCAN_CODE"
    }
}