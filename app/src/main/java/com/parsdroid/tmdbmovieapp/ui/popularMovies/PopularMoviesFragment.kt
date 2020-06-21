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
import androidx.recyclerview.widget.RecyclerView
import com.parsdroid.tmdbmovieapp.MyApp.Companion.appComponent
import com.parsdroid.tmdbmovieapp.R
import com.parsdroid.tmdbmovieapp.data.appModel.Movie
import com.parsdroid.tmdbmovieapp.ui.ResponseState
import com.parsdroid.tmdbmovieapp.ui.adapter.PopularMoviesAdapter
import com.parsdroid.tmdbmovieapp.util.myLogTag
import kotlinx.android.synthetic.main.fragment_popular_movies.*
import javax.inject.Inject


class PopularMoviesFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: PopularMoviesViewModelFactory
    private val viewModel: PopularMoviesViewModel by viewModels(factoryProducer = { viewModelFactory })
    private lateinit var rvAdapter: PopularMoviesAdapter

    // for load more
    private val visibleThreshold = 1
    private var lastVisibleItem: Int = 0
    private var totalItemCount: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_popular_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        appComponent.inject(this)
        setupRecyclerView()
        setupRecyclerViewScrollListener()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        rvAdapter = PopularMoviesAdapter(itemClickListener = {
            Toast.makeText(
                context,
                "item clicked: ${rvAdapter.getItem(it)?.title}",
                Toast.LENGTH_SHORT
            ).show()
            printLog("item clicked")
        })

        rvList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = rvAdapter
        }
    }

    private fun setupRecyclerViewScrollListener() {
        val layoutManager: LinearLayoutManager = rvList.layoutManager as LinearLayoutManager
        rvList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                totalItemCount = layoutManager.itemCount
                lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                if (!rvAdapter.isLoading && totalItemCount <= lastVisibleItem + visibleThreshold) {
                    loadMore()
                }
            }

            private fun loadMore() {
                printLog("end of list")
                viewModel.loadPopularMovies()
                printLog(viewModel.nextPage.toString())
            }
        })
    }

    private fun observeViewModel() {
        viewModel.apply {
            loadMoviesState.observe(viewLifecycleOwner, Observer {
                printLog(it.toString())
                when (it) {
                    is ResponseState.Error -> {
                        printLog("Error: ${it.exception.message.toString()}")
                        rvAdapter.setLoading(false)
                        if (rvAdapter.itemCount == 0)
                            showCorrespondingLayout(RecyclerViewState.ERROR)
                        else
                            Toast.makeText(
                                context,
                                "Error: ${it.exception.message.toString()}",
                                Toast.LENGTH_SHORT
                            ).show()
                    }
                    is ResponseState.Success<List<Movie>> -> {
                        rvAdapter.setLoading(false)
                        rvAdapter.addItems(it.data)
                        if (rvAdapter.itemCount == 0)
                            showCorrespondingLayout(RecyclerViewState.EMPTY)
                        else
                            showCorrespondingLayout(RecyclerViewState.SUCCESS)
                    }
                    ResponseState.Loading -> {
                        if (rvAdapter.itemCount == 0)
                            showCorrespondingLayout(RecyclerViewState.LOADING)
                        else
                            rvAdapter.setLoading(true)
                    }
                }
            })
        }
    }

    private fun showCorrespondingLayout(state: RecyclerViewState) {
        when (state) {
            RecyclerViewState.SUCCESS -> {
                rvList.visibility = View.VISIBLE
                rvLoadingLayout.visibility = View.INVISIBLE
                rvErrorLayout.visibility = View.INVISIBLE
                rvEmptyLayout.visibility = View.INVISIBLE
            }
            RecyclerViewState.LOADING -> {
                rvList.visibility = View.INVISIBLE
                rvLoadingLayout.visibility = View.VISIBLE
                rvErrorLayout.visibility = View.INVISIBLE
                rvEmptyLayout.visibility = View.INVISIBLE
            }
            RecyclerViewState.ERROR -> {
                rvList.visibility = View.INVISIBLE
                rvLoadingLayout.visibility = View.INVISIBLE
                rvErrorLayout.visibility = View.VISIBLE
                rvEmptyLayout.visibility = View.INVISIBLE
            }
            RecyclerViewState.EMPTY -> {
                rvList.visibility = View.INVISIBLE
                rvLoadingLayout.visibility = View.INVISIBLE
                rvErrorLayout.visibility = View.INVISIBLE
                rvEmptyLayout.visibility = View.VISIBLE
            }
        }
    }

    private fun printLog(text: String) {
        Log.d(myLogTag, text)
    }
}

enum class RecyclerViewState {
    ERROR,
    SUCCESS,
    LOADING,
    EMPTY
}