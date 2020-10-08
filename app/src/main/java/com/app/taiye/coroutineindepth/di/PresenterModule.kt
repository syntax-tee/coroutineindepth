
package com.app.taiye.coroutineindepth.di

import com.app.taiye.coroutineindepth.ui.movies.MoviesPresenter
import com.app.taiye.coroutineindepth.ui.movies.MoviesPresenterImpl
import org.koin.dsl.module

/**
 * Creates presenters for the app.
 */

fun presenterModule() = module {
  single { MoviesPresenterImpl(get()) as MoviesPresenter }
}