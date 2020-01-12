package com.example.architecturestudy.data.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.architecturestudy.data.Entity.MovieEntity

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movieItem: List<MovieEntity>)
}