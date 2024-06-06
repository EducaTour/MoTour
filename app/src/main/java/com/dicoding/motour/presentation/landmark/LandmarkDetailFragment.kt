package com.dicoding.motour.presentation.landmark

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicoding.motour.R

class LandmarkDetailFragment : Fragment() {

    companion object {
        fun newInstance() = LandmarkDetailFragment()
    }

    private val viewModel: LandmarkDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_landmark_detail, container, false)
    }
}