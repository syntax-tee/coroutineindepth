
package com.app.taiye.coroutineindepth.di

import com.app.taiye.coroutineindepth.contextProvider.CoroutineContextProvider
import com.app.taiye.coroutineindepth.contextProvider.CoroutineContextProviderImpl
import com.app.taiye.coroutineindepth.data.database.MovieDatabase
import com.app.taiye.coroutineindepth.domain.repository.MovieRepository
import com.app.taiye.coroutineindepth.domain.repository.MovieRepositoryImpl
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

fun appModule() = module {

  single { CoroutineContextProviderImpl(Dispatchers.IO) as CoroutineContextProvider  }

  single { MovieDatabase.create(androidContext()) } // database

  single { get<MovieDatabase>().movieDao() } // dao

  single { MovieRepositoryImpl(get(), get(), get()) as MovieRepository } // repository
}