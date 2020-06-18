package com.parsdroid.tmdbmovieapp.popularMovies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.parsdroid.tmdbmovieapp.MyApp.Companion.appComponent
import com.parsdroid.tmdbmovieapp.databinding.FragmentPopularMoviesBinding
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

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.apply {
            dataLoading.observe(viewLifecycleOwner, Observer { printLog(it.toString()) })
            items.observe(viewLifecycleOwner, Observer { printLog(it.toString()) })
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

}