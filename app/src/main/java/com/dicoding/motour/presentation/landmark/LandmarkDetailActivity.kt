package com.dicoding.motour.presentation.landmark

import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.dicoding.motour.R
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

            val regular = landmark.ticketPrice?.regular
            val adult = landmark.ticketPrice?.adult
            val child = landmark.ticketPrice?.child
            val student = landmark.ticketPrice?.student
            val foreigner = landmark.ticketPrice?.foreign
            val ultimatePackage = landmark.ticketPrice?.ultimate
            val otherPackage = landmark.ticketPrice?.packageB

            if (regular != null) tvLandmarkPriceRegular.text = formatPrice(regular) else regularPriceLayout.visibility = View.GONE
            if (adult != null) tvLandmarkPriceAdult.text = formatPrice(adult) else adultPriceLayout.visibility = View.GONE
            if (child != null) tvLandmarkPriceChild.text = formatPrice(child) else childPriceLayout.visibility = View.GONE
            if (student != null) tvLandmarkPriceStudent.text = formatPrice(student) else studentPriceLayout.visibility = View.GONE
            if (foreigner != null) tvLandmarkPriceForeigner.text = formatPrice(foreigner) else foreignerPriceLayout.visibility = View.GONE
            if (ultimatePackage != null) tvLandmarkPricePackages.text = formatPrice(ultimatePackage) else packagingPriceLayout.visibility = View.GONE
            if (otherPackage != null) tvLandmarkPricePackages.text = formatPrice(otherPackage) else packagingPriceLayout.visibility = View.GONE

            tvLandmarkOpenMonday.text = landmark.openingHours?.monday ?: "-"
            tvLandmarkOpenTuesday.text = landmark.openingHours?.tuesday ?: "-"
            tvLandmarkOpenWednesday.text = landmark.openingHours?.wednesday ?: "-"
            tvLandmarkOpenThursday.text = landmark.openingHours?.thursday ?: "-"
            tvLandmarkOpenFriday.text = landmark.openingHours?.friday ?: "-"
            tvLandmarkOpenSaturday.text = landmark.openingHours?.saturday ?: "-"
            tvLandmarkOpenSunday.text = landmark.openingHours?.sunday ?: "-"

            tvLandmarkContactEmail.text = landmark.contactInfo?.email ?: "-"
            tvLandmarkContactPhone.text = landmark.contactInfo?.phone ?: "-"

            val website = landmark.contactInfo?.website

            if (website != null && website != "-") {
                tvLandmarkContactWebsite.text = website
                tvLandmarkContactWebsite.paintFlags = Paint.UNDERLINE_TEXT_FLAG
                tvLandmarkContactWebsite.setOnClickListener {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(website)
                    )
                    startActivity(intent)
                }
            } else {
                tvLandmarkContactWebsite.text = "-"
            }

            if (landmark.uniqueActivities.isNotEmpty()) {
                tvLandmarkUniqueActivities.text = makeBulletedList(landmark.uniqueActivities)
            } else {
                uniqueActivitiesHeader.visibility = View.GONE
                tvLandmarkUniqueActivities.visibility = View.GONE
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

    private fun formatPrice(price: String) = "± Rp. $price"

    private fun showLoading(state: Boolean) {
        binding.progressIndicator.visibility = if (state) View.VISIBLE else View.GONE
    }

    private fun showError(state: Boolean) {
        binding.ivError.visibility = if (state) View.VISIBLE else View.GONE
        binding.btnRetry.visibility = if (state) View.VISIBLE else View.GONE
        if (state) {
            Toast.makeText(
                this,
                getString(R.string.network_error),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    companion object {
        const val EXTRA_LANDMARK_ID = "EXTRA_LANDMARK_ID"
    }
}