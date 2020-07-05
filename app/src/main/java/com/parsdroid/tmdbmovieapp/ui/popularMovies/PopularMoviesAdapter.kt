package com.parsdroid.tmdbmovieapp.ui.popularMovies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.parsdroid.tmdbmovieapp.data.db.entity.Movie
import com.parsdroid.tmdbmovieapp.databinding.ItemLoadingBinding
import com.parsdroid.tmdbmovieapp.databinding.ItemRowMovieBinding
import com.parsdroid.tmdbmovieapp.util.IMAGE_BASE_URL


class PopularMoviesAdapter(
    private val items: MutableList<Movie?> = mutableListOf(),
    private val itemClickListener: ((Int) -> Unit)? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1
    var isLoading: Boolean = false
        private set

    fun addItems(newItems: List<Movie>) {
        if (newItems.isNotEmpty()) {
            val oldSize = this.items.size
            this.items.addAll(newItems)
            notifyItemRangeInserted(oldSize, newItems.size)
        }
    }

    //Returning view for each item in the lis
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ITEM) {
            ItemViewHolder(
                ItemRowMovieBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                ), itemClickListener
            )
        } else {
            LoadingViewHolder(
                ItemLoadingBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
            )
        }
    }

    //Binding the data on the list
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            items[position]?.let { bindItemRow(holder, it) }
//            items[position]?.let { holder.bind(it) }
        } else if (holder is LoadingViewHolder) {
            items[position]?.let { /* Do something or nothing */ }
//            items[position]?.let { holder.bind(it) }
        }
    }

    private fun bindItemRow(holder: ItemViewHolder, item: Movie) {
        with(holder) {
            with(item) {
                // TODO (bind all of view)
                Glide.with(holder.itemView).load(IMAGE_BASE_URL + this.posterPath)
                    .into(itemBinding.moviePoster)
                itemBinding.movieReleaseDate.text = this.releaseDate
                itemBinding.movieTitle.text = this.title
                // TODO (get genres title and bind it)
                var genresList: String = ""
//                this.genres?.forEach {
//                    genresList += "$it, "
//                }
                itemBinding.movieGenre.text = genresList
            }
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return if (items[position] == null) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
    }

    fun getItem(position: Int) = items[position]

    fun setLoading(loading: Boolean) {
        if (items.size != 0) {
            if (loading) {
                isLoading = true
                items.add(null)
                notifyItemInserted(items.size - 1)
            } else {
                isLoading = false
                if (items.last() == null) {
                    items.removeAt(items.size - 1)
                    notifyItemRemoved(items.size)
                }
            }
        }
    }


    class ItemViewHolder(
        val itemBinding: ItemRowMovieBinding,
        private val clickListener: ((Int) -> Unit)? = null
    ) : RecyclerView.ViewHolder(itemBinding.root) {
//        fun bind(item: Movie) {
//            with(item) {
//                // TODO (bind all of view)
//                Glide.with(itemView).load(IMAGE_BASE_URL + this.posterPath)
//                    .into(itemBinding.moviePoster)
//                itemBinding.movieReleaseDate.text = this.releaseDate
//                itemBinding.movieTitle.text = this.title
//                // TODO (get genres title and bind it)
//                var genresList: String = ""
//                this.genres?.forEach {
//                    genresList += "$it, "
//                }
//                itemBinding.movieGenre.text = genresList
//            }
//        }

        init {
            itemBinding.root.setOnClickListener { clickListener?.invoke(adapterPosition) }
        }
    }

    private class LoadingViewHolder(val itemBinding: ItemLoadingBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: Movie) {
            /* Do something or nothing */
        }
    }
}