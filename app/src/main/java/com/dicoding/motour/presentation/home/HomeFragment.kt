package com.dicoding.motour.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.motour.R
import com.dicoding.motour.data.model.landmark.list.Landmark
import com.dicoding.motour.databinding.FragmentHomeBinding
import com.dicoding.motour.presentation.di.Injector
import com.dicoding.motour.presentation.landmark.LandmarkDetailActivity
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeFragment : Fragment() {
    @Inject
    lateinit var factory: HomeViewModelFactory
    private lateinit var viewModel: HomeViewModel

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: HomeAdapter

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as Injector).createHomeSubComponent().inject(this)
        viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewCompat.setOnApplyWindowInsetsListener(binding.headerContent) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(0, systemBars.top, 0, 0)
            insets
        }

        adapter = HomeAdapter()

        with(binding) {
            rvLandmark.layoutManager = LinearLayoutManager(requireContext())
            rvLandmark.setHasFixedSize(true)
        }

        fetchLandmarkList()

        viewModel.landmarkList.observe(viewLifecycleOwner) {
            when (it) {
                is HomeViewModel.LandmarkListState.Loading -> {
                    showLoading(true)
                    showError(false)
                }

                is HomeViewModel.LandmarkListState.Result -> {
                    showLoading(false)
                    showError(false)

                    val landmarkList = it.landmarkList

                    adapter.submitList(landmarkList)
                    binding.rvLandmark.adapter = adapter

                    adapter.setOnItemClickListener(object : HomeAdapter.OnItemClickListener {
                        override fun onItemClick(landmark: Landmark) {
                            Intent(requireContext(), LandmarkDetailActivity::class.java).apply {
                                putExtra(LandmarkDetailActivity.EXTRA_LANDMARK_ID, landmark.id)
                            }.run {
                                startActivity(this)
                            }
                        }
                    })
                }

                is HomeViewModel.LandmarkListState.Error -> {
                    showLoading(false)
                    showError(true)
                }
            }
        }

        binding.btnRetry.setOnClickListener {
            fetchLandmarkList()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun fetchLandmarkList() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getLandmarkList()
        }
    }

    private fun showLoading(state: Boolean) {
        binding.progressIndicator.visibility = if (state) View.VISIBLE else View.GONE
    }

    private fun showError(state: Boolean) {
        binding.ivError.visibility = if (state) View.VISIBLE else View.GONE
        binding.btnRetry.visibility = if (state) View.VISIBLE else View.GONE
        Toast.makeText(
            requireContext(),
            getString(R.string.network_error),
            Toast.LENGTH_SHORT
        ).show()
    }
}