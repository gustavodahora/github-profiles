package dev.gustavodahora.githubprofiles.ui.home

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.squareup.picasso.Picasso
import dev.gustavodahora.githubprofiles.R
import dev.gustavodahora.githubprofiles.databinding.FragmentProfilesBinding


class ProfilesFragment : Fragment() {

    private var _binding: FragmentProfilesBinding? = null
    private lateinit var profilesViewModel: ProfilesViewModel

    private lateinit var urlLink: String

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        profilesViewModel =
            ViewModelProvider(this)[ProfilesViewModel::class.java]

        _binding = FragmentProfilesBinding.inflate(inflater, container, false)

        observers()
        listeners()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observers() {
        profilesViewModel.userProfile.observe(viewLifecycleOwner) {
            Picasso.get().load(it.avatarUrl).into(binding.imgAvatar)
            binding.tvName.text = it.name
            binding.tvUsername.text = it.login
            binding.tvFollowersCount.text = it.followers.toString()
            binding.tvRepoCount.text = it.publicRepos.toString()
            urlLink = "https://github.com/" + binding.editUsername.text.toString()

            showCardProfile()
        }
        profilesViewModel.errorStatus.observe(viewLifecycleOwner) {
            if (it) showDialogError()
        }
        // Progress circle infinity
    }

    private fun listeners() {
        val editUsername: TextInputEditText = binding.editUsername
        val btnSearch: Button = binding.btnSearch
        btnSearch.setOnClickListener {
            profilesViewModel.retrofitCall(editUsername.text.toString())
            hideKeyboard()
        }
        binding.ctnLayout.setOnClickListener {
            startActivity(
                Intent(
                    "android.intent.action.VIEW",
                    Uri.parse(urlLink)
                )
            )
        }
    }

    private fun showCardProfile() {
        if (!binding.cardViewUser.isVisible) {
            YoYo.with(Techniques.FadeInLeft)
                .duration(2000)
                .onStart { binding.cardViewUser.visibility = View.VISIBLE }
                .playOn(binding.cardViewUser)
        }
    }

    private fun showDialogError() {
        context?.let {
            MaterialAlertDialogBuilder(it)
                .setTitle(resources.getString(R.string.not_found))
                .setMessage(resources.getString(R.string.supporting_text))
                .setPositiveButton(resources.getString(R.string.ok)) { _, _ ->
                }.show()
        }
    }

    private fun hideKeyboard() {
        val imm =
            requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = requireActivity().currentFocus
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}