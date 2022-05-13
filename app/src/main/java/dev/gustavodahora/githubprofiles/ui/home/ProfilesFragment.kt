package dev.gustavodahora.githubprofiles.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.squareup.picasso.Picasso
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
        }
        // Import material for error
        // Progress circle infinity
        // Yoyo For animations
    }

    private fun listeners() {
        val editUsername: TextInputEditText = binding.editUsername
        val btnSearch: Button = binding.btnSearch
        btnSearch.setOnClickListener {
            profilesViewModel.retrofitCall(editUsername.text.toString())
        }
    }
}