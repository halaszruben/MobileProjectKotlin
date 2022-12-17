package com.example.worldofwarcraftquiz.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HeroDao {

    @Query("SELECT * FROM hero")
    fun getAllHeroes(): List<Hero>

    @Insert
    fun insertHeroes(vararg hero: Hero)
}