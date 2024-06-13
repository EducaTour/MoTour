package com.dicoding.motour.presentation.landmark

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.ViewModelProvider
import com.dicoding.motour.databinding.ActivityLandmarkDetailBinding
import com.dicoding.motour.presentation.di.Injector
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
        viewModel.getLandmarkById(landmarkId!!).observe(this) {
            val landmark = it!! // Shouldn't be null at all

            binding.tvLandmarkName.text = landmark.name
            Log.d("LANDMARK_DETAIL", it.toString())
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
    }

    companion object {
        const val EXTRA_LANDMARK_ID = "EXTRA_LANDMARK_ID"
    }
}