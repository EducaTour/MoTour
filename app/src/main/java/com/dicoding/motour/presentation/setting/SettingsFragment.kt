package com.dicoding.motour.presentation.setting

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.dicoding.motour.R
import com.dicoding.motour.data.preferences.LanguageUtil
import com.dicoding.motour.databinding.FragmentSettingsBinding
import com.dicoding.motour.presentation.di.Injector
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.jakewharton.processphoenix.ProcessPhoenix
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class SettingsFragment : Fragment() {

    @Inject
    lateinit var factory: SettingsViewModelFactory
    private lateinit var viewModel: SettingsViewModel

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as Injector).createSettingsSubComponent().inject(this)
        viewModel = ViewModelProvider(this, factory)[SettingsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewCompat.setOnApplyWindowInsetsListener(binding.tvHeader) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(0, systemBars.top, 0, 0)
            insets
        }

        setupClickListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupClickListener() {
        binding.ivIndonesia.setOnClickListener {
            runBlocking { changePreferredLanguage(LanguageUtil.ContentLanguage.Indonesia) }
            restartAppForLanguage()
        }
        binding.ivEnglish.setOnClickListener {
            runBlocking { changePreferredLanguage(LanguageUtil.ContentLanguage.English) }
            restartAppForLanguage()
        }
        binding.ivChina.setOnClickListener {
            runBlocking { changePreferredLanguage(LanguageUtil.ContentLanguage.Chinese) }
            restartAppForLanguage()
        }
        binding.itemFaq.setOnClickListener {
            Intent(requireContext(), FaqActivity::class.java).run {
                startActivity(this)
            }
        }
        binding.itemAbout.setOnClickListener {
            Intent(requireContext(), AboutActivity::class.java).run {
                startActivity(this)
            }
        }
    }

    private fun changePreferredLanguage(language: LanguageUtil.ContentLanguage) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.changePreferredLanguage(language)
        }
    }

    private fun restartAppForLanguage() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.application_restart))
            .setMessage(getString(R.string.application_will_now_restart_to_change_the_preferred_language))
            .setPositiveButton(getString(R.string.close)) { _, _ ->
                ProcessPhoenix.triggerRebirth(requireContext())
            }
            .setOnCancelListener {
                ProcessPhoenix.triggerRebirth(requireContext())
            }
            .show()
    }
}