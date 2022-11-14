package ru.kosteloff.moviegoer.screens

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import ru.kosteloff.moviegoer.R
import ru.kosteloff.moviegoer.adapter.MainAdapter
import ru.kosteloff.moviegoer.databinding.FragmentMainBinding
import ru.kosteloff.moviegoer.viewmodel.MovieViewModel

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MovieViewModel by viewModels()
    lateinit var recyclerView: RecyclerView
    lateinit var mainAdapter: MainAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        setHasOptionsMenu(true)
        viewModel.initDatabase()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun init() {
        recyclerView = binding.rvMain
        mainAdapter = MainAdapter()
        viewModel.getMoviesRetrofit()
        recyclerView.adapter = mainAdapter

        viewModel.mutableLiveData.observe(viewLifecycleOwner) {
            it.body()?.let { it1 -> mainAdapter.setList(it1.result) }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu -> {
                findNavController().navigate(R.id.action_mainFragment_to_favoriteFragment)
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}