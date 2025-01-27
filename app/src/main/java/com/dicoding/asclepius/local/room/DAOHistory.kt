package com.dicoding.asclepius.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.dicoding.asclepius.local.entity.HistoryEntity

@Dao
interface DAOHistory {
    @Insert
    suspend fun insertPrediction(history: HistoryEntity)

    @Query("SELECT * FROM history_table ORDER BY imageUri DESC")
    suspend fun getAllHistoryPrediction(): List<HistoryEntity>

    @Delete
    suspend fun deletePrediction(history: HistoryEntity)
}