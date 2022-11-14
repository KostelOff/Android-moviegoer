package ru.kosteloff.moviegoer.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import ru.kosteloff.moviegoer.adapter.FavoriteAdapter
import ru.kosteloff.moviegoer.databinding.FragmentFavoriteBinding
import ru.kosteloff.moviegoer.viewmodel.MovieViewModel

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    lateinit var recyclerView: RecyclerView
    lateinit var favoriteAdapter: FavoriteAdapter
    private val viewModel: MovieViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        favoriteAdapter = FavoriteAdapter()
        recyclerView = binding.rvFavorite
        recyclerView.adapter = favoriteAdapter
        viewModel.getAllMovies().observe(viewLifecycleOwner) {
            favoriteAdapter.setList(it.asReversed())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}