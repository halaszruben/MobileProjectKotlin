package com.example.worldofwarcraftquiz.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Hero(
    @ColumnInfo(name = "hero_name") val name: String,
    @ColumnInfo(name = "class_name") val `class`: String,
    @ColumnInfo(name = "iLvL") val iLvL: Int,
    @ColumnInfo(name = "active_player") val stillPlaying: Boolean
) {
        @PrimaryKey(autoGenerate = true) var id: Int? = null
}