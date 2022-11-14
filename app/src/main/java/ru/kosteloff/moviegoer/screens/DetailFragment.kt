package ru.kosteloff.moviegoer.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import ru.kosteloff.moviegoer.R
import ru.kosteloff.moviegoer.adapter.IMAGE_URL
import ru.kosteloff.moviegoer.databinding.FragmentDetailBinding
import ru.kosteloff.moviegoer.model.Result
import ru.kosteloff.moviegoer.sharedpreference.RepositoryShared
import ru.kosteloff.moviegoer.viewmodel.MovieViewModel

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    lateinit var currentMovie: Result
    private val args by navArgs<DetailFragmentArgs>()
    private val viewModel: MovieViewModel by viewModels()
    private var isFavorite = false
    lateinit var shared: RepositoryShared

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFavorite()
        favoriteButton()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initFavorite() {
        with(binding) {
            binding.tvTitle.text = args.currentMoview.title
            tvDateRelease.text = args.currentMoview.releaseDate

            Glide.with(this@DetailFragment)
                .load("$IMAGE_URL${currentMovie.posterPath}")
                .centerCrop()
                .placeholder(R.drawable.tape)
                .into(binding.ivAvatarMovie)
        }
    }

    private fun favoriteButton() {
        shared = RepositoryShared()
        val value =
            context?.let { shared.getValue(it, currentMovie.id.toString(), currentMovie.title) }

        if (isFavorite != value) {
            binding.ivFavoriteStar.setImageResource(R.drawable.ic_baseline_star_24_yellow)
        } else {
            binding.ivFavoriteStar.setImageResource(R.drawable.ic_baseline_star_24_white)
        }

        shared = RepositoryShared()
        val valueFavorite =
            context?.let { shared.getValue(it, currentMovie.id.toString(), currentMovie.title) }

        binding.ivFavoriteStar.setOnClickListener {
            if (isFavorite == valueFavorite) {
                binding.ivFavoriteStar.setImageResource(R.drawable.ic_baseline_star_24_yellow)
                context?.let { it1 ->
                    shared.saveValue(
                        it1,
                        true,
                        currentMovie.id.toString(),
                        currentMovie.title
                    )
                }
                viewModel.insert(currentMovie)
                isFavorite = true
            } else {
                binding.ivFavoriteStar.setImageResource(R.drawable.ic_baseline_star_24_white)
                viewModel.delete(currentMovie)
                context?.let { it1 ->
                    shared.saveValue(
                        it1,
                        false,
                        currentMovie.id.toString(),
                        currentMovie.title
                    )
                }
                isFavorite = false
            }
        }
    }
}