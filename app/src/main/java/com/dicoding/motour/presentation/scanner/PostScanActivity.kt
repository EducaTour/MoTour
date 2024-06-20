package com.dicoding.motour.presentation.scanner

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dicoding.motour.data.model.landmark.detail.LANDMARKS_KEY
import com.dicoding.motour.databinding.ActivityPostScanBinding
import com.dicoding.motour.presentation.di.Injector
import com.dicoding.motour.utils.Result
import com.dicoding.motour.utils.reduceFileImage
import com.dicoding.motour.utils.uriToFile
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import javax.inject.Inject

class PostScanActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ScannerViewModelFactory
    private lateinit var viewModel: ScannerViewModel
    private lateinit var binding: ActivityPostScanBinding
    private lateinit var chosenImageUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()

        super.onCreate(savedInstanceState)
        binding = ActivityPostScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (application as Injector).createScannerSubComponent().inject(this)
        viewModel = ViewModelProvider(this, factory)[ScannerViewModel::class.java]

        setupEdgeToEdge()
        setupClickListener()
        setupResult()
    }

    private fun setupScanResult() {
        chosenImageUri.let { uri ->
            val file = uriToFile(uri, this).reduceFileImage()
            val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
            val body = MultipartBody.Part.createFormData(
                "image",
                file.name,
                requestFile
            )
            viewModel.getScanner(body).observe(this) { result ->
                when (result) {
                    is Result.Loading -> {
                        showLoading(true)
                    }

                    is Result.Success -> {
                        showLoading(false)
                        val response = result.data
                        val scanResult = response.code()
                        val intent = Intent(this, ResultScanActivity::class.java)
                        if (scanResult == 201) {
                            val data = response.body()!!
                            intent.apply {
                                putExtra(ResultScanActivity.EXTRA_SCAN_ID, LANDMARKS_KEY[data.result])
                                putExtra(ResultScanActivity.EXTRA_SCAN_RESULT, data.result)
                                putExtra(ResultScanActivity.EXTRA_SCAN_RATE, data.rate)
                                putExtra(ResultScanActivity.EXTRA_SCAN_IMAGE, data.image)
                                putExtra(ResultScanActivity.EXTRA_SCAN_CREATED_AT, data.createdAt)
                                putExtra(ResultScanActivity.EXTRA_SCAN_CODE, scanResult.toString())
                            }
                        } else {
                            intent.apply {
                                putExtra(ResultScanActivity.EXTRA_SCAN_CODE, scanResult.toString())
                            }
                        }
                        startActivity(intent)
                        finish()
                    }

                    is Result.Error -> {
                        showLoading(false)
                        Toast.makeText(
                            this,
                            "Error: ${result.exception.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun setupEdgeToEdge() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.topLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(0, systemBars.top, 0, 0)
            insets
        }
    }

    private fun setupClickListener() {
        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        binding.btnScan.setOnClickListener {
            setupScanResult()
        }
    }

    private fun setupResult() {
        val scanCode = intent.getIntExtra(ScanActivity.EXTRA_SCAN_STATUS_CODE, -1)
        val image = intent.getStringExtra(ScanActivity.EXTRA_SCAN_URI)
        chosenImageUri = Uri.parse(image)

        when (scanCode) {
            ScanActivity.SCAN_SUCCESS -> {
                Glide.with(binding.root)
                    .load(chosenImageUri)
                    .into(binding.ivLandmark)
            }

            ScanActivity.SCAN_FAILED_PREDICTION -> {
                Toast.makeText(this, "Failed to predict", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressIndicator.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}