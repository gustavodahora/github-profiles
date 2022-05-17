package dev.gustavodahora.convidados.view.viewholder

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.gustavodahora.githubprofiles.R
import dev.gustavodahora.githubprofiles.services.model.Repos

class ReposViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    interface Callbacks {
        fun onItemClicked(tvRepoLink: String)
    }

    fun bind(repo: Repos) {
        val tvRepoName = itemView.findViewById<TextView>(R.id.tv_repo_name)
        val tvRepoLink = itemView.findViewById<TextView>(R.id.tv_repo_link)
        val lnRow = itemView.findViewById<LinearLayout>(R.id.ln_row)
        tvRepoName.text = repo.name
        tvRepoLink.text = repo.fullName

//        val callbacks: Callbacks = Callbacks.onItemClicked()
//
//        lnRow.setOnClickListener {
//            Callbacks.onItemClicked(repo.fullName)
//        }
    }
}