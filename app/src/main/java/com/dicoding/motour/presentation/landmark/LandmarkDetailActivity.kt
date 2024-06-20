package com.dicoding.motour.presentation.landmark

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.dicoding.motour.data.model.landmark.detail.LandmarkDetail
import com.dicoding.motour.databinding.ActivityLandmarkDetailBinding
import com.dicoding.motour.presentation.di.Injector
import kotlinx.coroutines.launch
import javax.inject.Inject

class LandmarkDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLandmarkDetailBinding
    private var landmarkId: Int? = null

    @Inject
    lateinit var factory: LandmarkDetailViewModelFactory
    private lateinit var viewModel: LandmarkDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLandmarkDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (application as Injector).createLandmarkSubComponent().inject(this)
        viewModel = ViewModelProvider(this, factory)[LandmarkDetailViewModel::class.java]

        landmarkId = intent.getIntExtra(EXTRA_LANDMARK_ID, -1)

        setupEdgeToEdge()
        setupScrollListener()
        setupClickListener()

        if (landmarkId != -1) {
            loadLandmark()
        }
    }

    private fun loadLandmark() {

        fetchLandmarkData()

        viewModel.landmark.observe(this) {
            when (it) {
                is LandmarkDetailViewModel.LandmarkDetailState.Loading -> {
                    showLoading(true)
                    showError(false)
                }

                is LandmarkDetailViewModel.LandmarkDetailState.Result -> {
                    showLoading(false)
                    showError(false)

                    val landmark = it.landmark

                    if (landmark != null) {
                        loadLandmarkData(landmark)
                    }

                    binding.nestedScrollview.visibility = View.VISIBLE
                }

                is LandmarkDetailViewModel.LandmarkDetailState.Error -> {
                    showLoading(false)
                    showError(true)
                }
            }
        }
    }

    private fun setupEdgeToEdge() {
        val windowInsetsController =
            WindowCompat.getInsetsController(window, window.decorView)

        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        ViewCompat.setOnApplyWindowInsetsListener(window.decorView) { view, windowInsets ->
            windowInsetsController.hide(WindowInsetsCompat.Type.navigationBars())
            ViewCompat.onApplyWindowInsets(view, windowInsets)
        }
    }

    private fun setupScrollListener() {
        val fab = binding.fabBack

        binding.nestedScrollview.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY > oldScrollY + 12 && fab.isExtended) {
                fab.shrink()
                fab.hide()
            }

            if (scrollY < oldScrollY - 12 && !fab.isExtended) {
                fab.show()
                fab.extend()
            }

            if (scrollY == 0) {
                fab.show()
                fab.extend()
            }
        }
    }

    private fun setupClickListener() {
        binding.fabBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        binding.btnRetry.setOnClickListener {
            fetchLandmarkData()
        }
    }

    private fun fetchLandmarkData() {
        lifecycleScope.launch {
            viewModel.getLandmarkById(landmarkId!!)
        }
    }

    private fun loadLandmarkData(landmark: LandmarkDetail) {
        with(binding) {
            Glide
                .with(root)
                .load(landmark.photos[0])
                .into(ivLandmarkHeader)

            Glide
                .with(root)
                .load(landmark.photos[1])
                .into(ivImageOne)

            tvLandmarkName.text = landmark.name
            tvLandmarkDescription.text = landmark.description
            tvLandmarkHistory.text = landmark.history

            tvLandmarkAddress.text = landmark.location?.address ?: "-"
            tvLandmarkMapUrl.setOnClickListener {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(landmark.location!!.locationUrl)
                )
                startActivity(intent)
            }

            tvLandmarkPriceAdult.text = "Rp. ${landmark.ticketPrice?.adult ?: "-"}"
            tvLandmarkPriceChild.text = "Rp. ${landmark.ticketPrice?.child ?: "-"}"

            tvLandmarkOpenMonday.text = landmark.openingHours?.monday ?: "-"
            tvLandmarkOpenTuesday.text = landmark.openingHours?.tuesday ?: "-"
            tvLandmarkOpenWednesday.text = landmark.openingHours?.wednesday ?: "-"
            tvLandmarkOpenThursday.text = landmark.openingHours?.thursday ?: "-"
            tvLandmarkOpenFriday.text = landmark.openingHours?.friday ?: "-"
            tvLandmarkOpenSaturday.text = landmark.openingHours?.saturday ?: "-"
            tvLandmarkOpenSunday.text = landmark.openingHours?.sunday ?: "-"

            tvLandmarkContactEmail.text = landmark.contactInfo?.email ?: "-"
            tvLandmarkContactPhone.text = landmark.contactInfo?.phone ?: "-"
            tvLandmarkContactWebsite.text = landmark.contactInfo?.website ?: "-"

            if (landmark.uniqueActivities.isNotEmpty()) {
                tvLandmarkUniqueActivities.text = makeBulletedList(landmark.uniqueActivities)
            } else {
                tvLandmarkUniqueActivities.text = "-"
            }
        }
    }

    private fun makeBulletedList(items: List<String?>): String {
        val builder = StringBuilder()
        for (item in items) {
            builder.appendLine("• $item")
        }
        return builder.toString().trim()
    }

    private fun showLoading(state: Boolean) {
        binding.progressIndicator.visibility = if (state) View.VISIBLE else View.GONE
    }

    private fun showError(state: Boolean) {
        binding.ivError.visibility = if (state) View.VISIBLE else View.GONE
        binding.btnRetry.visibility = if (state) View.VISIBLE else View.GONE
    }

    companion object {
        const val EXTRA_LANDMARK_ID = "EXTRA_LANDMARK_ID"
    }
}