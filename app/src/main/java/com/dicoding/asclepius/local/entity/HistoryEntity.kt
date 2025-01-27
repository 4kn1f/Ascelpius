package com.dicoding.asclepius.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_table")
data class HistoryEntity (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "imageUri")
    val imageUri: String,
    @ColumnInfo(name = "prediction")
    val prediction: String,
    @ColumnInfo(name = "confidenceScore")
    val confidenceScore: Int
)