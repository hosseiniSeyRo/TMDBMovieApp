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
import com.parsdroid.tmdbmovieapp.R
import com.parsdroid.tmdbmovieapp.ui.adapter.PopularMoviesAdapter
import com.parsdroid.tmdbmovieapp.util.myLogTag
import kotlinx.android.synthetic.main.fragment_popular_movies.*
import javax.inject.Inject


class PopularMoviesFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: PopularMoviesViewModelFactory
    private val viewModel: PopularMoviesViewModel by viewModels(factoryProducer = { viewModelFactory })

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
        observeViewModel()
    }

    private fun setupRecyclerView() {
        rvEmptyLayout.visibility = View.INVISIBLE
        rvLoadingLayout.visibility = View.INVISIBLE

        rvList.apply {
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
                (rvList.adapter as PopularMoviesAdapter).addItems(it)
                showRecyclerView(rvList.adapter?.itemCount != 0)
            })
            snackbarMessage.observe(viewLifecycleOwner, Observer { printLog(it) })
        }
    }

    private fun printLog(text: String) {
        Log.d(myLogTag, text)
    }

    private fun showLoading(show: Boolean) {
        if (show) rvLoadingLayout.visibility =
            View.VISIBLE else rvLoadingLayout.visibility = View.INVISIBLE
    }

    private fun showRecyclerView(show: Boolean) {
        if (show) {
            rvList.visibility = View.VISIBLE
            rvEmptyLayout.visibility = View.INVISIBLE
        } else {
            rvList.visibility = View.INVISIBLE
            rvEmptyLayout.visibility = View.VISIBLE
        }
    }

}