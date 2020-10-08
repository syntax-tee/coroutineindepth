
package com.app.taiye.coroutineindepth.di

import com.app.taiye.coroutineindepth.data.database.MovieDatabase
import com.app.taiye.coroutineindepth.domain.repository.MovieRepository
import com.app.taiye.coroutineindepth.domain.repository.MovieRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

fun appModule() = module {

  single { MovieDatabase.create(androidContext()) } // database

  single { get<MovieDatabase>().movieDao() } // dao

  single { MovieRepositoryImpl(get(), get()) as MovieRepository } // repository
}