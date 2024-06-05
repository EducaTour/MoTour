package com.dicoding.motour.presentation.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.dicoding.motour.R
import com.dicoding.motour.presentation.di.Injector
import javax.inject.Inject

class HomeFragment : Fragment() {
    @Inject
    lateinit var factory: HomeViewModelFactory
    private lateinit var viewModel: HomeViewModel

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
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getLandmarkList().observe(viewLifecycleOwner) { landmarkList ->
            Log.i("MYTAG", landmarkList.toString())
        }

        // Set up button click listener
        view.findViewById<Button>(R.id.button_example).setOnClickListener {
            Log.i("MYTAG", "Button was clicked!")
        }
    }
}