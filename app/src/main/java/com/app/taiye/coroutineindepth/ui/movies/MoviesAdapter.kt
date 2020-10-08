
package com.app.taiye.coroutineindepth.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.taiye.coroutineindepth.R
import com.bumptech.glide.Glide
import com.app.taiye.coroutineindepth.di.MOVIE_IMAGE_BASE_PATH
import com.app.taiye.coroutineindepth.data.model.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

/**
 * Inflates and displays the [Movie] data in a list.
 * */
class MovieAdapter : RecyclerView.Adapter<MovieViewHolder>() {

  private val items = mutableListOf<Movie>()

  fun setData(newItems: List<Movie>) {
    items.clear()
    items.addAll(newItems)
    notifyDataSetChanged()
  }

  override fun getItemCount() = items.size

  override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
    holder.showData(items[position])
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)

    return MovieViewHolder(view)
  }
}

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

  fun showData(movie: Movie) = with(itemView) {
    Glide.with(this)
        .load(MOVIE_IMAGE_BASE_PATH + movie.posterPath)
        .into(movieImage)
  }
}