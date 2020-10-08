package com.app.taiye.coroutineindepth.ui.movies

import android.content.ContentValues.TAG
import android.util.Log
import com.app.taiye.coroutineindepth.domain.repository.MovieRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Handles the business logic calls, reacting to UI events.
 */
class MoviesPresenterImpl(private val movieRepository: MovieRepository) : MoviesPresenter, CoroutineScope {

    private var parenjob = SupervisorJob()

    private lateinit var moviesView: MoviesView

    override fun setView(moviesView: MoviesView) {
        this.moviesView = moviesView
    }

    override fun getData() {
        launch {
            delay(500)
            val result = movieRepository.getMovies()

            Log.d(TAG, "getData: Still Alive!")
            if (result.value != null && result.value.isNotEmpty()) {
                moviesView.showMovies(result.value)
            } else if (result.throwable != null) {
                handleError(result.throwable)
            }
        }
    }
    private fun handleError(throwable: Throwable) {
        moviesView.showError(throwable)
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + parenjob



    override fun stop() {
        parenjob.cancelChildren()
    }
}