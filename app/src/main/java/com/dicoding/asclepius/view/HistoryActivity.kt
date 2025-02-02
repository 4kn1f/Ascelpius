package com.dicoding.asclepius.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.asclepius.databinding.ActivityHistoryBinding
import com.dicoding.asclepius.local.entity.HistoryEntity
import com.dicoding.asclepius.local.room.HistoryRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding
    private lateinit var database: HistoryRoomDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = HistoryRoomDatabase.getDatabase(this)

        binding.recyclerViewHistory.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            val historyList = withContext(Dispatchers.IO) {
                database.historyDao().getAllHistoryPrediction()
            }
            binding.recyclerViewHistory.adapter = HistoryAdapter(historyList.toMutableList()) { history ->
                deletePrediction(history)
            }
        }
    }

    private fun deletePrediction(history: HistoryEntity) {
        lifecycleScope.launch(Dispatchers.IO) {
            database.historyDao().deletePrediction(history)
            val updatedHistoryList = database.historyDao().getAllHistoryPrediction()
            withContext(Dispatchers.Main) {
                (binding.recyclerViewHistory.adapter as HistoryAdapter).updateHistory(updatedHistoryList)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
