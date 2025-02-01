package com.dicoding.asclepius.view

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.asclepius.databinding.ItemHistoryBinding
import com.dicoding.asclepius.local.entity.HistoryEntity

class HistoryAdapter(private var historyList: MutableList<HistoryEntity>, private val onDeleteClick: (HistoryEntity) -> Unit) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(historyList[position])
    }

    override fun getItemCount(): Int = historyList.size

    inner class HistoryViewHolder(private val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(history: HistoryEntity) {
            binding.imageHistory.setImageURI(Uri.parse(history.imageUri))
            binding.textPrediction.text = "Prediction: ${history.prediction}"
            binding.textConfidence.text = "Confidence: ${history.confidenceScore}%"

            binding.deleteIcon.setOnClickListener {
                onDeleteClick(history)
            }
        }
    }

    fun updateHistory(newHistoryList: List<HistoryEntity>) {
        historyList.clear()
        historyList.addAll(newHistoryList)
        notifyDataSetChanged()
    }
}
