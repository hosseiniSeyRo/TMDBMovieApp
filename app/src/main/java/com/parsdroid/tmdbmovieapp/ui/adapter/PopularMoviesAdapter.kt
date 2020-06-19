package com.parsdroid.tmdbmovieapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.parsdroid.tmdbmovieapp.data.appModel.Movie
import com.parsdroid.tmdbmovieapp.databinding.RowMovieItemBinding


class PopularMoviesAdapter(
    private val items: MutableList<Movie> = mutableListOf(),
    private val itemClickListener: ((Int) -> Unit)? = null
) : RecyclerView.Adapter<PopularMoviesAdapter.MyVH>() {

    fun addItems(newItems: List<Movie>) {
        val oldSize = this.items.size
        this.items.addAll(newItems)
        notifyItemRangeInserted(oldSize, newItems.size)
    }

    //Returning view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVH {
        return MyVH(
            RowMovieItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            ), itemClickListener
        );
    }

    override fun getItemCount(): Int = items.size
    fun getItemAt(position: Int) = items[position]

    //Binding the data on the list
    override fun onBindViewHolder(holder: MyVH, position: Int) {
        with(holder) {
            with(items[position]) {
                // TODO
                itemBinding.movieReleaseDate.text = releaseDate
                itemBinding.movieTitle.text = title
                var genresList: String = ""
                genres?.forEach {
                    genresList += "$it, "
                }
                itemBinding.movieGenre.text = genresList
            }
        }
    }

    //Class holds the list view
    class MyVH(
        val itemBinding: RowMovieItemBinding,
        private val clickListener: ((Int) -> Unit)? = null
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        init {
            itemBinding.root.setOnClickListener { clickListener?.invoke(adapterPosition) }
        }
    }
}