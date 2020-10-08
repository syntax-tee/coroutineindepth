package com.app.taiye.coroutineindepth.domain.repository

import com.app.taiye.coroutineindepth.data.model.Movie
import com.app.taiye.coroutineindepth.data.model.Result

/**
 * Interface used to communicate to the end entities, when fetching data.
 */
interface MovieRepository {

  suspend  fun getMovies(): Result<List<Movie>>
}