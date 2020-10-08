
package com.app.taiye.coroutineindepth.ui.movies

import com.app.taiye.coroutineindepth.data.model.Movie

/**
 *
 */
interface MoviesView {

  fun showMovies(movies: List<Movie>)

  fun showError(throwable: Throwable)
}