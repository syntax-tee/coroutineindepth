package com.app.taiye.coroutineindepth.ui.movies

import com.app.taiye.coroutineindepth.domain.repository.MovieRepository

/**
 * Handles the business logic calls, reacting to UI events.
 */
class MoviesPresenterImpl(private val movieRepository: MovieRepository) : MoviesPresenter {

    private lateinit var moviesView: MoviesView

    override fun setView(moviesView: MoviesView) {
        this.moviesView = moviesView
    }

    override fun getData() {
        movieRepository.getMovies(
            onMoviesReceived = { movies -> moviesView.showMovies(movies) },
            onError = { throwable -> handleError(throwable) }
        )
    }

    private fun handleError(throwable: Throwable) {
        moviesView.showError(throwable)
    }
}