package com.app.taiye.coroutineindepth.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.taiye.coroutineindepth.data.model.Movie

/**
 * Room database to store and fetch movies.
 */

@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {

  abstract fun movieDao(): MovieDao

  companion object {

    fun create(context: Context): MovieDatabase {

      return Room.databaseBuilder(
          context,
          MovieDatabase::class.java,
          "movies"
      ).build()
    }
  }
}