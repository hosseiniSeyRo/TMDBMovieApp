package com.parsdroid.tmdbmovieapp.ui.popularMovies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.parsdroid.tmdbmovieapp.MyApp.Companion.appComponent
import com.parsdroid.tmdbmovieapp.databinding.FragmentPopularMoviesBinding
import com.parsdroid.tmdbmovieapp.ui.adapter.PopularMoviesAdapter
import com.parsdroid.tmdbmovieapp.util.myLogTag
import javax.inject.Inject


class PopularMoviesFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: PopularMoviesViewModelFactory
    private val viewModel: PopularMoviesViewModel by viewModels(factoryProducer = { viewModelFactory })
    private var _binding: FragmentPopularMoviesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPopularMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        appComponent.inject(this)
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        binding.rvEmptyLayout.visibility = View.INVISIBLE
        binding.rvLoadingLayout.visibility = View.INVISIBLE

        binding.rvList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = PopularMoviesAdapter(itemClickListener = {
                Toast.makeText(
                    context,
                    "item clicked: ${(this.adapter as PopularMoviesAdapter).getItemAt(it).title}",
                    Toast.LENGTH_SHORT
                ).show()
                printLog("item clicked")
            })
        }
    }

    private fun observeViewModel() {
        viewModel.apply {
            dataLoading.observe(viewLifecycleOwner, Observer {
                printLog(it.toString())
                showLoading(it)
            })
            items.observe(viewLifecycleOwner, Observer {
                printLog(it.toString())
                (binding.rvList.adapter as PopularMoviesAdapter).addItems(it)
                showRecyclerView(binding.rvList.adapter?.itemCount != 0)
            })
            snackbarMessage.observe(viewLifecycleOwner, Observer { printLog(it) })
        }
    }

    private fun printLog(text: String) {
        Log.d(myLogTag, text)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showLoading(show: Boolean) {
        if (show) binding.rvLoadingLayout.visibility =
            View.VISIBLE else binding.rvLoadingLayout.visibility =
            View.INVISIBLE
    }

    private fun showRecyclerView(show: Boolean) {
        if (show) {
            binding.rvList.visibility = View.VISIBLE
            binding.rvEmptyLayout.visibility = View.INVISIBLE
        } else {
            binding.rvList.visibility = View.INVISIBLE
            binding.rvEmptyLayout.visibility = View.VISIBLE
        }
    }

}