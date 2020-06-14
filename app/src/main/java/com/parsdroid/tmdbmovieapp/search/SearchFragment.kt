package com.parsdroid.tmdbmovieapp.search

import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.parsdroid.tmdbmovieapp.R


class SearchFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_search, container, false)

        val btn: Button = rootView.findViewById(R.id.buttonNextPage)
        btn.setOnClickListener { view ->
            val action = SearchFragmentDirections.actionBottomNavSearchToBottomNavSearch222()
            action.id = 25
            view.findNavController().navigate(action)
        }

        return rootView
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_menu, menu)
    }

}