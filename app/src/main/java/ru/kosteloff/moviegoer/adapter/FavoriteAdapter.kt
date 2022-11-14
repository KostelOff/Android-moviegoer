package ru.kosteloff.moviegoer.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.kosteloff.moviegoer.R
import ru.kosteloff.moviegoer.databinding.LayoutFavoriteBinding
import ru.kosteloff.moviegoer.model.Result
import ru.kosteloff.moviegoer.screens.FavoriteFragmentDirections

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.FavoriteHolder>() {

    var movieList = emptyList<Result>()

    class FavoriteHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = LayoutFavoriteBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_favorite, parent, false)
        return FavoriteHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteHolder, position: Int) {
        holder.binding.apply {
            tvTitleMain.text = movieList[position].title
            dateReleaseMain.text = movieList[position].releaseDate
            tvPopularity.text = movieList[position].popularity.toString()

            holder.binding.layoutFavoriteId.setOnClickListener {
                val action =
                    FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment(movieList[position])
                holder.binding.layoutFavoriteId.findNavController().navigate(action)
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