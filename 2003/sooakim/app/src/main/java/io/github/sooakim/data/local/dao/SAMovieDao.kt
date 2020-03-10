package io.github.sooakim.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.sooakim.data.local.model.SAMovieEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface SAMovieDao {
    @Query("SELECT * FROM movie")
    fun getMovies(): Single<List<SAMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<SAMovieEntity>): Completable

    @Query("DELETE from movie")
    fun deleteAll(): Completable
}