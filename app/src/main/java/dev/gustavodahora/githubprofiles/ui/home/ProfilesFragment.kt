package dev.gustavodahora.githubprofiles.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
}