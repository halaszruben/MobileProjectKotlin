package com.example.worldofwarcraftquiz.db

import androidx.room.*

@Dao
abstract class HeroesDao {

    @Query("SELECT * FROM heroes_table")
    abstract fun getAll(): List<Heroes>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insert(heroes: Heroes)

    @Query("DELETE FROM heroes_table")
    abstract suspend fun deleteAll()
}