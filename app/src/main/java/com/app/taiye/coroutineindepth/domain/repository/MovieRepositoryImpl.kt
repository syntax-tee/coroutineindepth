package com.app.taiye.coroutineindepth.domain.repository

import com.app.taiye.coroutineindepth.contextProvider.CoroutineContextProvider
import com.app.taiye.coroutineindepth.data.api.MovieApiService
import com.app.taiye.coroutineindepth.data.database.MovieDao
import com.app.taiye.coroutineindepth.di.API_KEY
import com.app.taiye.coroutineindepth.data.model.Movie
import com.app.taiye.coroutineindepth.data.model.MoviesResponse
import com.app.taiye.coroutineindepth.data.model.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

/**
 * Connects to the end entity, and exposes functionality to the user.
 */
class MovieRepositoryImpl(
    private val movieApiService: MovieApiService,
    private val movieDao: MovieDao,
    private  val contextProvider: CoroutineContextProvider
) : MovieRepository {

    override suspend fun getMovies(): Result<List<Movie>> = withContext(contextProvider.context()) {

        val resultDeferred = async { movieApiService.getMovies(API_KEY).execute() }
        val cachedMoviesDeferred = async { movieDao.getSavedMovies() }

        val cachedMovies = cachedMoviesDeferred.await()

        try {

            val result = resultDeferred.await()
            val moviesResponse = result.body()?.movies

            if (result.isSuccessful && moviesResponse != null) {
                Result(moviesResponse, null)
            } else {
                Result(cachedMovies, null)
            }
        } catch (error: Throwable) {
            if (error is IOException && cachedMovies.isEmpty()) {
                Result(null, error)
            } else {
                Result(cachedMovies, null)
            }
        }
    }
}