package ru.kosteloff.moviegoer.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.kosteloff.moviegoer.R
import ru.kosteloff.moviegoer.databinding.LayoutMainBinding
import ru.kosteloff.moviegoer.model.Result
import ru.kosteloff.moviegoer.screens.MainFragmentDirections

const val IMAGE_URL = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2"

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainHolder>() {

    var movieList = emptyList<Result>()

    class MainHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = LayoutMainBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_main, parent, false)
        return MainHolder(view)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.binding.apply {
            tvTitleMain.text = movieList[position].title
            dateReleaseMain.text = movieList[position].releaseDate
            tvPopularity.text = movieList[position].popularity.toString()

            holder.binding.layoutMainId.setOnClickListener {
                val action =
                    MainFragmentDirections.actionMainFragmentToDetailFragment(movieList[position])
                holder.binding.layoutMainId.findNavController().navigate(action)
            }

            Glide
                .with(holder.itemView)
                .load("$IMAGE_URL${movieList[position].posterPath}")
                .centerCrop()
                .placeholder(R.drawable.tape)
                .into(holder.binding.ivAvatarMainLayout)
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<Result>) {
        movieList = list
        notifyDataSetChanged()
    }
}