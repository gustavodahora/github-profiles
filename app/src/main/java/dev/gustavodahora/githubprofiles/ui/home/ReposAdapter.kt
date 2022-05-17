package dev.gustavodahora.convidados.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.gustavodahora.convidados.view.viewholder.ReposViewHolder
import dev.gustavodahora.githubprofiles.R
import dev.gustavodahora.githubprofiles.services.model.Repos

class ReposAdapter : RecyclerView.Adapter<ReposViewHolder>() {

    private var mGuestList: List<Repos> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.row_repo, parent, false)
        return ReposViewHolder(item)
    }

    override fun getItemCount(): Int {
        return if (mGuestList.count() > 4) 4 else mGuestList.count()
    }

    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) {
        holder.bind(mGuestList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateRepos(list: List<Repos>?) {
        if (list != null) {
            mGuestList = list
        }
        notifyDataSetChanged()
    }
}