package com.app.taiye.coroutineindepth.ui.movies

import com.app.taiye.coroutineindepth.ui.movies.MoviesView

interface MoviesPresenter {

  fun setView(moviesView: MoviesView)

  fun getData()

  fun stop()
}
