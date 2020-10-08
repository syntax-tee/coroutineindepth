package com.app.taiye.coroutineindepth

import android.app.Application
import com.app.taiye.coroutineindepth.di.appModule
import com.app.taiye.coroutineindepth.di.networkingModule
import com.app.taiye.coroutineindepth.di.presenterModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Holds and starts the application and all the dependencies.
 */
class MoviesApp : Application() {

  override fun onCreate() {
    super.onCreate()

    startKoin {
      androidContext(this@MoviesApp)
      modules(listOf(appModule(), networkingModule(), presenterModule()))
    }
  }
}