package dev.gustavodahora.githubprofiles.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dev.gustavodahora.githubprofiles.databinding.FragmentSettingsBinding
import dev.gustavodahora.githubprofiles.model.ThemeStatus

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: SettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(this)[SettingsViewModel::class.java]

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

        listeners()
        observers()
        getTheme()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun listeners() {
        binding.switchCompat.setOnClickListener {
            viewModel.setTheme(binding.switchCompat.isChecked)
        }
    }

    private fun observers() {
        viewModel.themeSetup.observe(viewLifecycleOwner, this::setTheme)
    }

    private fun setTheme(theme: ThemeStatus) {
        when(theme) {
            ThemeStatus.LIGHT -> binding.switchCompat.isChecked = false
            ThemeStatus.DARK -> binding.switchCompat.isChecked = true
        }
    }

    private fun getTheme() {
        viewModel.getTheme()
    }
}