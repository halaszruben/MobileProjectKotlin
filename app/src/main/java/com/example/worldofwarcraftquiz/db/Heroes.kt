package com.example.worldofwarcraftquiz.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "heroes_table")
data class Heroes(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "hero_name") val heroName: String?,
    @ColumnInfo(name = "spec") val spec: String?,
    @ColumnInfo(name = "expansion") val expansion: String?
)
