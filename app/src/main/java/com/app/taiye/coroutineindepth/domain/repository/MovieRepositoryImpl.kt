package com.app.taiye.coroutineindepth.domain.repository

import com.app.taiye.coroutineindepth.data.api.MovieApiService
import com.app.taiye.coroutineindepth.data.database.MovieDao
import com.app.taiye.coroutineindepth.di.API_KEY
import com.app.taiye.coroutineindepth.data.model.Movie
import com.app.taiye.coroutineindepth.data.model.MoviesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

/**
 * Connects to the end entity, and exposes functionality to the user.
 */
class MovieRepositoryImpl(
  private val movieApiService: MovieApiService,
  private val movieDao: MovieDao
) : MovieRepository {

  override fun getMovies(
    onMoviesReceived: (List<Movie>) -> Unit,
    onError: (Throwable) -> Unit
  ) {
    movieApiService.getMovies(API_KEY).enqueue(object : Callback<MoviesResponse> {
      override fun onFailure(call: Call<MoviesResponse>, throwable: Throwable) {
        val savedMovies = movieDao.getSavedMovies()

        /**
         * If there's no internet connection, default to the cached values.
         * Otherwise propagate the error.
         * */
        if (throwable is IOException && savedMovies.isNotEmpty()) {
          onMoviesReceived(savedMovies)
        } else {
          onError(throwable)
        }
      }

      override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
        val movies = response.body()?.movies ?: emptyList()

        if (movies.isNotEmpty()) {
          movieDao.saveMovies(movies)
        }
        onMoviesReceived(movies)
      }
    })
  }
}